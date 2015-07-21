package restapi;

public interface RestApiCallListener {
	
	/*
	 * Interface to handle response
	 */
	
	void onResponse(String response, int pageId);
	void onError(String error);

}
