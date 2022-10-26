package tw.com.ispan.eeit48.domain;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReCaptchaResponse {

    private boolean success;
    private String challenge_ts;
    private String hostname;

    @JsonProperty("error-codes")
    private String[] errorCodes;
    
    public String[] getErrorCodes() {
		return errorCodes;
	}
    
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getChallenge_ts() {
        return challenge_ts;
    }

    public void setChallenge_ts(String challenge_ts) {
        this.challenge_ts = challenge_ts;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Override
	public String toString() {
		return "ReCaptchaResponse [success=" + success + ", challenge_ts=" + challenge_ts + ", hostname=" + hostname
				+ ", errorCodes=" + Arrays.toString(errorCodes) + "]";
	}

    
}
