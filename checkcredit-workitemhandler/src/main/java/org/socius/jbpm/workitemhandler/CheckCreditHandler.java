package org.socius.jbpm.workitemhandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;

import com.squareup.okhttp.OkHttpClient;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;


public class CheckCreditHandler implements WorkItemHandler {
	
	public void abortWorkItem(WorkItem arg0, WorkItemManager arg1) {
		// TODO Auto-generated method stub
		System.out.println("I am aborted");

	}

	public void executeWorkItem( WorkItem wrk, WorkItemManager manager) {
		
		
		
		
		
		    final OkHttpClient okHttpClient = new OkHttpClient();
		    okHttpClient.setReadTimeout(5, TimeUnit.MINUTES);
		    okHttpClient.setConnectTimeout(5, TimeUnit.MINUTES);


		
		Retrofit retrofit = new Retrofit.Builder()
			    .baseUrl("http://jbpmtestserver-sociusbpmsuite.rhcloud.com")
			    .addConverterFactory(GsonConverterFactory.create())	
			    .client(okHttpClient)
			    .build();
				
			GitHubService service = retrofit.create(GitHubService.class);
			Call<CheckList> res=service.getCheckList();
			Call<Order> orderRes=service.getOrder();
			try {
				CheckList check=res.execute().body();
				Order orders=orderRes.execute().body();
				System.out.println("The order Rest call result product number:"+orders.getProduct().size());
				
				Map<String , Object> results = new HashMap<String, Object>();
//				results.put("restResult", check.getId());
				results.put("global", 143);
				System.out.println("local inside work item"+check.getAmount());
				results.put("local",check.getAmount() );
//				results.put("order_score", check.getOrderScore());
				System.out.println("completeWorkItem");
				
				
					manager.completeWorkItem(wrk.getId(), results);
					
					
		
				
				System.out.println("====================completed work item============== ");
				
		
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Something went wrong in rest call:"+e.getLocalizedMessage());
				
			}
			

		



//		ClientConfig cc = new ClientConfig().register(new JacksonFeature());
//		WebTarget target = ClientBuilder
//		        .newClient(cc)
//		        .target("http://jbpmtestserver-sociusbpmsuite.rhcloud.com/jbpmTest.json");
//		Response response = target
//		        .request(MediaType.APPLICATION_JSON_TYPE)
//		        .get();
//		if( response != null ){
//			CheckList kfz = response.readEntity(CheckList.class);
//			System.out.println(kfz.toString());
//			System.out.println("Amount is "+kfz.getAmount());
//			response.close();
//			
//			Map<String , Object> results = new HashMap<String, Object>();
//			results.put("order_id", kfz.getId());
//			results.put("order_amount", kfz.getAmount());
//			results.put("order_score", kfz.getOrderScore());
//			manager.completeWorkItem(wrk.getId(), results);
//			
//		}
	

	}
	public interface GitHubService {
		  
		  @GET("/jbpmTest.json")
		  Call <CheckList> getCheckList();
		  
		  @GET("/orderSummary.json")
		  Call <Order> getOrder();
		}
		
	

}
