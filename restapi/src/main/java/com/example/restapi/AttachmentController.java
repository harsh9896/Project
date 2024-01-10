package com.example.restapi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class AttachmentController {
	
	AttachmentRepository attachmentRepository;
	ArticlesRepository articlesRepository;
	public AttachmentController(AttachmentRepository attachmentRepository, ArticlesRepository articlesRepository)
	{
		super();
		this.attachmentRepository = attachmentRepository;
		this.articlesRepository = articlesRepository;
	}
	
	@PostMapping("/upload/{id}")
	public ResponseData uploadFile(@RequestParam("file") MultipartFile file, @PathVariable int id) throws Exception
	{		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if(fileName.contains("..")) {
				throw new Exception("File Name contains invalid path sequence" + fileName);
			}
			
			Attachment attachment = new Attachment(fileName, file.getContentType(), file.getBytes());
			Articles article = articlesRepository.getById(id);
			article.setAttachment(attachment);
			attachmentRepository.save(attachment);
			String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/download/")
//					.path(username+'/')
					.path(attachment.getId())
					.toUriString();
			String viewURL = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/view/")
//					.path(username+'/')
					.path(attachment.getId())
					.toUriString();
			article.setViewURL(viewURL);
			article.setDownloadURL(downloadURL);
			articlesRepository.save(article);
			return new ResponseData(attachment.getFileName(), downloadURL, file.getContentType(), file.getSize());
		}
		catch(Error e)
		{
			throw new Exception("Could not save file");
		}
	}
	
	@GetMapping("/download/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileId) throws Exception
	{
		Attachment attachment = attachmentRepository.findById(fileId).orElseThrow(()-> new Exception("File not found with Id" + fileId));
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(attachment.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getFileName() + "\"" )
				.body(new ByteArrayResource(attachment.getData()));
		
	}
	
	@GetMapping("/view/{fileId}")
	public ResponseEntity<ByteArrayResource> viewFile(@PathVariable String fileId) throws Exception
	{
		Attachment attachment = attachmentRepository.findById(fileId).orElseThrow(()-> new Exception("File not found with Id" + fileId));
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(attachment.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + attachment.getFileName() + "\"" )
				.body(new ByteArrayResource(attachment.getData()));
		
	}


}
