package com.qts.icam.utility.chartmanager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.qts.icam.model.common.ChartQueryData;

public class ChartQueryManager {

	private List<ChartQueryData> queriesList;
	private Map<String, List<ChartQueryData>> queriesMap;
	
	public Map<String, List<ChartQueryData>> loadSqlXMLFile(String reportQueryPath) throws ParserConfigurationException, IOException, SAXException{
    	
		File queryXmlFile = new File(reportQueryPath);
    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    	Document doc = dBuilder.parse(queryXmlFile);
    	
    	doc.getDocumentElement().normalize();

        if (queriesMap == null){
        	queriesMap = new HashMap<String, List<ChartQueryData>>();
        }
        
        NodeList moduleList = doc.getElementsByTagName("MODULE");
        
        for (int moduleCounter = 0; moduleCounter < moduleList.getLength(); moduleCounter++) {
        	
        	Node moduleNode = moduleList.item(moduleCounter);
        	
        	String moduleId = null;
        	
        	if (moduleNode.getNodeType() == Node.ELEMENT_NODE) {
        		
        		Element moduleElement = (Element) moduleNode;
        		
        		moduleId = moduleElement.getAttribute("ID");
        		
        		queriesList = new ArrayList<ChartQueryData>();
        		
        		NodeList sqlList = moduleNode.getChildNodes();
        		for (int sqlCounter = 0; sqlCounter < sqlList.getLength(); sqlCounter++) {
        			
        			ChartQueryData queryData = null;
        			Node sqlNode = sqlList.item(sqlCounter);
        			
        			if (sqlNode.getNodeType() == Node.ELEMENT_NODE) {
        				
        				queryData = new ChartQueryData();
        				Element sqlElement = (Element) sqlNode;
            			queryData.setSqlId(sqlElement.getAttribute("ID"));
            			
            			NodeList childList = sqlNode.getChildNodes();
            			
            			for (int count = 0; count < childList.getLength(); count++) {
                    		
                    		Node childNode = childList.item(count);
                    		
                    		if (childNode.getNodeType() == Node.COMMENT_NODE) {
                        		
                    			queryData.setSql(childNode.getTextContent()); 
                        	}
                    		
                    		if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                    			if("role".equalsIgnoreCase(childNode.getNodeName())){
                    				queryData.setRole(childNode.getTextContent());
                    			}
                    			if("default".equalsIgnoreCase(childNode.getNodeName())){
                    				boolean isdefault = false;
                    				if("true".equalsIgnoreCase(childNode.getTextContent())){
                    					isdefault = true;
                    				}
                    				queryData.setDefault(isdefault);
                    			}
                    			if("charttype".equalsIgnoreCase(childNode.getNodeName())){
                    				queryData.setChartType(childNode.getTextContent());
                    			}
                    			if("chartlabel".equalsIgnoreCase(childNode.getNodeName())){
                    				queryData.setChartLabel(childNode.getTextContent());
                    			}
                    			if("caption".equalsIgnoreCase(childNode.getNodeName())){
                    				queryData.setCaption(childNode.getTextContent());
                    			}
                    			if("subcaption".equalsIgnoreCase(childNode.getNodeName())){
                    				queryData.setSubCaption(childNode.getTextContent());
                    			}
                    			if("xaxisname".equalsIgnoreCase(childNode.getNodeName())){
                    				queryData.setXaxisname(childNode.getTextContent());
                    			}
                    			if("yaxianame".equalsIgnoreCase(childNode.getNodeName())){
                    				queryData.setYaxisname(childNode.getTextContent());
                    			}
                    			if("numberprefix".equalsIgnoreCase(childNode.getNodeName())){
                    				queryData.setNumberprefix(childNode.getTextContent());
                    			}
                    			if("theme".equalsIgnoreCase(childNode.getNodeName())){
                    				queryData.setTheme(childNode.getTextContent());
                    			}
                        		
                        	}
                    	}
            			if(queryData.isDefault()){
            				queriesList.add(queryData);
            			}
            			
        			}
        			
        		}
        		
        		queriesMap.put(moduleId, queriesList);
        		
        	}
        }
        
        for (String key : queriesMap.keySet()){
        	
        	List<ChartQueryData> queriesList = queriesMap.get(key);
//        	for(ChartQueryData queryData : queriesList){
//        		System.out.println(queryData.toString());
//        	}
        	
        }
        
        return queriesMap;
    }
}
