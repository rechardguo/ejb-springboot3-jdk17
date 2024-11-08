package rechard.learn;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

    @PostMapping("/upload")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile[] files,
                                   @RequestParam("username") String username,
                                   @RequestParam("description") String description) {

        MultipartFile file=files[0];
        // 处理文件上传逻辑
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                // 保存文件或其他处理逻辑
                System.out.println("文件名称: " + file.getOriginalFilename());
                System.out.println("用户名: " + username);
                System.out.println("描述: " + description);
                return "文件上传成功";
            } catch (Exception e) {
                return "文件上传失败: " + e.getMessage();
            }
        } else {
            return "请选择文件进行上传";
        }
    }
    @GetMapping("/goto-upload")
    public String uploadPage() throws IOException {
		return "upload";        
	}

}
