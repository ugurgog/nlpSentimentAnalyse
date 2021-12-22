package com.uren.extranet.api.model;

import java.io.Serializable;

public class SentimentAnalysisModel implements Serializable{

	private static final long serialVersionUID = 967205949913505255L;

	private String text;
	private String language;
	private int negativeSentiment;
	private int positiveSentiment;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getNegativeSentiment() {
		return negativeSentiment;
	}
	public void setNegativeSentiment(int negativeSentiment) {
		this.negativeSentiment = negativeSentiment;
	}
	public int getPositiveSentiment() {
		return positiveSentiment;
	}
	public void setPositiveSentiment(int positiveSentiment) {
		this.positiveSentiment = positiveSentiment;
	}
}
