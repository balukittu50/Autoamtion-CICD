//package SeleniumFramework.Data;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.HashMap;
//import java.util.List;
//
//import org.apache.commons.io.FileUtils;
//import org.testng.annotations.BeforeMethod;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import SeleniumFramework.PageObjects.LandingPage;
//
//public class DataReader {
////	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
////		// read json to string
////
////		String jsonContent = FileUtils.readFileToString(new File(
////				System.getProperty("user.dir") + "\\src\\test\\java\\SeleniumFramework\\Data\\PurchaseOrder.json"),
////				StandardCharsets.UTF_8);
////
////		// string to HasMap Jackson DataBind
////		ObjectMapper mapper = new ObjectMapper();
////		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
////				new TypeReference<List<HashMap<String, String>>>() {
////				});
////		return data;
//////{map}{map1}
//		
//	}
//	
//	
//}
