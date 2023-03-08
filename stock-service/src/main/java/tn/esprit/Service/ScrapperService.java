package tn.esprit.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import tn.esprit.Entity.Offer;
import tn.esprit.Entity.Product;
import tn.esprit.Interface.IOfferService;
import tn.esprit.Interface.IProductService;
import tn.esprit.Interface.IScrapperService;
import tn.esprit.Repository.IProductRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Documented;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class ScrapperService {

    IProductRepository productRepository;
    IProductService productService;

    IOfferService offerService;


    public List<Product> run() {
        // Add your web scraping logic here
        // For example, you can use the Jsoup library to scrape data from a web page:
        Document doc;
      /*  try {
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
        }*/
        List<Product> avaialbe_products = new ArrayList<>();
        List<Product> productList= productService.getMostOrderedProduct();
        String data = getUrls("https://www.tunisianet.com.tn/420-carte-mere");
        System.out.println(data);
       // List<Product> products = productRepository.getListProductByName(name);
        for(Product product : productList)
        {
            if(data.toLowerCase().contains(product.getName_product().toLowerCase()))
            {
              avaialbe_products.add(product);
              product.getId();
              //offerService.addOfferAndAssignProductSupplier();
            }
        }
        log.info(avaialbe_products.toString());

        return avaialbe_products;
    }

    private String getUrls(String url) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String content = restTemplate.getForObject(url, String.class);
            return content;
        } catch (IllegalArgumentException illegalArgumentException) {
            log.error("url is not valid!");
            return "";
        } catch (Exception e) {
            log.error("Error!");
            return "";
        }
    }



    private Date convertCalendarToDate (int Year , int Mounth , int day) {
        Calendar cal = Calendar.getInstance();
        Mounth--;
        cal.set(Year, Mounth, day);
        Date date = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String date1 = format.format(date);
        Date getDate = null;
        try {
            getDate = format.parse(date1);
        }
        catch (Exception e){System.out.println(e.getMessage());}
        return  getDate;
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
