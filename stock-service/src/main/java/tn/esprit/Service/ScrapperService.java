package tn.esprit.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import tn.esprit.Entity.Product;
import tn.esprit.Interface.IProductService;
import tn.esprit.Interface.IScrapperService;
import tn.esprit.Repository.IProductRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Documented;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ScrapperService implements Runnable {

    IProductRepository productRepository;

    public void run() {
        // Add your web scraping logic here
        // For example, you can use the Jsoup library to scrape data from a web page:
        Document doc;
        try {
            doc = Jsoup.connect("https://www.tunisianet.com.tn/recherche?controller=search&s=MSI&submit_search=&page=2&order=product.price.asc").get();
            // Get the list of search results
            Elements results = doc.select("div.s-result-item");

            // Process each search result
            for (Element result : results) {
                // Get the product name
                String name = result.select("h2 a").text();

                // Search for products in the database
                List<Product> products = productRepository.getListProductByName(name);

                // Process the products as needed
                for (Product product : products) {
                    System.out.println("Found product: " + product.getName_product());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }










    //PythonInterpreter pythonInterpreter;

    /*@Override
    public String scrapeWebsite(String url) throws IOException {
        String PythonScriptPath = "src/main/python/scrape.py";
        String[] cmd =new String[2];
        cmd[0] = "python";
        cmd[1] = PythonScriptPath;
        ProcessBuilder pb = new ProcessBuilder(cmd);
        pb.directory(new File("src/main/python/"));
        pb.environment().put("SCRAPPING_URL",url);
        Process p = pb.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        StringBuilder output = new StringBuilder();
        while ((line = in.readLine()) != null){
            output.append(line);
        }
        return output.toString();
    }*/


    /*@Override
    public String scrapeWebsites(String url) throws IOException {
        String pythonScriptPath = "src/main/python/scrape.py";
        pythonInterpreter.execfile(pythonScriptPath);
        PyObject scrapedData = pythonInterpreter.get("title");
        return scrapedData.toString();
    }*/
}
