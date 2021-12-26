package com.uren.extranet.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.detectlanguage.DetectLanguage;
import com.detectlanguage.Result;
import com.detectlanguage.errors.APIError;
import com.google.gson.Gson;
import com.uren.extranet.api.model.SentimentAnalysisModel;
import com.uren.extranet.api.nlp.Pipeline;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

@Controller
@RequestMapping("/common")
public class LanguageController {

	private Logger logger = LoggerFactory.getLogger(LanguageController.class);

	private Gson gson;
	private Environment env;
	 
	@Autowired
    public LanguageController(Gson gson, Environment env) {
        this.gson = gson;
        this.env = env;
    }
	
	@RequestMapping(value="/detectLanguage", method=RequestMethod.GET)
	@ResponseBody
    public String detectLanguage(@RequestParam("text") String text) {
    	
		String apikey = env.getProperty("detect.language.api");
		
		DetectLanguage.apiKey = apikey;
		Result result = null;
		try {
			List<Result> results = DetectLanguage.detect(text);
			
			result = results.get(0);
			
			logger.info("::detectLanguage language:{}, isReliable:{}, confidence:{}", 
					result.language, result.isReliable, result.confidence);
		} catch (APIError e) {
			logger.error("::detectLanguage exception occured ", e);
		}
		
		if(result != null)
			return result.language;
		
		return "";
    }
	
	@RequestMapping(value="/sentimentAnalysis", method=RequestMethod.GET)
	@ResponseBody
    public ResponseEntity<?> getTextSentimentAnalysis(@RequestParam("text") String text) {
    	
		String language = detectLanguage(text);
		
		SentimentAnalysisModel model = new SentimentAnalysisModel();
		
		model.setText(text);
		
		if (language != null && !language.isEmpty()) {
			model.setLanguage(language);
			setSentimentAnalysis(model, language, text);
		}
		
		logger.info("::getTextSentimentAnalysis model:{}", gson.toJson(model));
			
		return new ResponseEntity<>(model, HttpStatus.OK);
    }
	
	private void setSentimentAnalysis(SentimentAnalysisModel model, String language, String text){
		if(language.equals("en")) {
			StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();

			CoreDocument coreDocument = new CoreDocument(text);
			stanfordCoreNLP.annotate(coreDocument);

			List<CoreSentence> coreSentences = coreDocument.sentences();

			for (CoreSentence sentence : coreSentences) {
				String sentiment = sentence.sentiment();
				
				if(sentiment.equalsIgnoreCase("Negative")) {
					model.setNegativeSentiment(model.getNegativeSentiment() + 1);
				}else if(sentiment.equalsIgnoreCase("Positive")) {
					model.setPositiveSentiment(model.getPositiveSentiment() + 1);
				}
				
				logger.info("::setSentimentAnalysis sentiment:{}", sentiment);
			}
		}
	}
}
