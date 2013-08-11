/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.controller.admin.TrackController.java
 * Class:			TrackController
 * Date:			2012-4-6
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.controller.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ketayao.pojo.Track;
import com.ketayao.pojo.TrackList;
import com.ketayao.system.Constants;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-6 下午10:07:40 
 */
@Controller
@RequestMapping("/admin/site")
public class TrackController {

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/readTrack", method=RequestMethod.GET)
	public String read(HttpServletRequest request) throws Exception {		
		String playlist = request.getSession().getServletContext().getRealPath("/") + "/styles/dewplayer/playlist.xml";
		
		SAXReader saxReader = new SAXReader();
		saxReader.setEncoding("UTF-8");
		File file = new File(playlist);
		Document document = saxReader.read(file);

		Element rootElement = document.getRootElement();
		Element tracksElement = rootElement.element("trackList");
		
		List<Track> tracks = new ArrayList<Track>();
		// 组装track
		Iterator<Element> eIterator = tracksElement.elementIterator();
		while (eIterator.hasNext()) {
			Track track = new Track();
			Element element = eIterator.next();

			track.setCreator(element.element("creator").getText());
			track.setLocation(element.element("location").getText());
			track.setTitle(element.element("title").getText());
			
			tracks.add(track);
		}
		
		request.setAttribute("tracks", tracks);
		
		return "admin/site/track-read";
	}

	@RequestMapping(value="/updateTrack", method=RequestMethod.POST)
	public String update(HttpServletRequest request, TrackList tracks) throws Exception {
		String playlist = request.getSession().getServletContext().getRealPath("/") + "/styles/dewplayer/playlist.xml";
		Document document = DocumentHelper.createDocument();

		Element rootElement = document.addElement("playlist");
		Element tracksElement = rootElement.addElement("trackList");

		for (Track track : tracks.getTracks()) {
			//	过滤掉title或者location为空的track
			if (track.getTitle().trim().equals("") || track.getLocation().trim().equals("")) {
				continue;
			}
			Element trackElement = tracksElement.addElement("track");
			trackElement.addElement("creator").setText(track.getCreator());
			trackElement.addElement("location").setText(track.getLocation());
			trackElement.addElement("title").setText(track.getTitle());
		}
		
		OutputFormat format = OutputFormat.createPrettyPrint();  
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream(new File(playlist)), format);
		writer.write(document);  
		writer.close();
		
		request.setAttribute(Constants.OPERATION_SUCCESS, Constants.OPERATION_SUCCESS);
		
		return read(request);
	}
}
