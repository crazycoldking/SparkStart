import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelGetRequest {

    public static void main(String[] args) {
        String uri = "https://example.com"; // Specify your URI here
        int numRequests = 100; // Number of requests to send
        
        ExecutorService executorService = Executors.newFixedThreadPool(numRequests);
        
        for (int i = 0; i < numRequests; i++) {
            int requestId = i + 1;
            
            executorService.submit(() -> {
                try {
                    URL url = new URL(uri);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    int responseCode = connection.getResponseCode();
                    String responseBody = readResponseBody(connection.getInputStream());

                    System.out.println("Request " + requestId + " - Response Code: " + responseCode);
                    System.out.println("Request " + requestId + " - Response Body: " + responseBody);
                    
                    connection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        
        executorService.shutdown();
    }
    
    private static String readResponseBody(InputStream inputStream) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder responseBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            responseBody.append(line);
        }
        reader.close();
        return responseBody.toString();
    }
}
