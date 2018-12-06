package yellow.util;

import org.springframework.web.multipart.MultipartFile;

public class UploadedImage {
private MultipartFile image;

public MultipartFile getImage() {
	return image;
}

public void setImage(MultipartFile image) {
	this.image = image;
}

}
