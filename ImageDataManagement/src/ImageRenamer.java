import java.io.File;
import java.io.IOException;

public class ImageRenamer {

	public static void main(String[] args) {
		try {
			final String basicBeforePath = "BeforeImages/";
			final String basicAfterPath = "afterImages/";
			File folder = new File("afterImages");
			if (!folder.exists())
				folder.mkdir();

			File tempFile = new File(basicAfterPath + "Temp"); // 임시파일 생성
			tempFile.createNewFile();

			File fileDirectory = new File(
					"C:\\Users\\latte\\git\\ImageDataManagement\\ImageDataManagement\\BeforeImages");
			String[] beforeImages = fileDirectory.list();

			for (String imageName : beforeImages) {
				File oldFile = new File(basicBeforePath + imageName);
				File newFile = new File(basicAfterPath + getNewFileName(imageName));
				oldFile.renameTo(newFile);
			}

			File noNames = new File("#noNames");
			File beforeFolder = new File("BeforeImages");
			beforeFolder.renameTo(noNames);
			beforeFolder.mkdir();
			tempFile.delete();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getNewFileName(String imageName) {
		String extension = imageName.substring(imageName.indexOf("."));
		if (imageName.substring(0, 5).equals("pixiv")) {
			String basicNum= imageName.substring(5, imageName.indexOf("."));
			return "illust_" + basicNum + extension;
		}
		else if (imageName.substring(0, 7).equals("illust_")) {
			String basicName = imageName.substring(7, imageName.indexOf("."));
			String basicNum = basicName.substring(0,basicName.indexOf("_")); 
			return "illust_" + basicNum + extension;
		}
		return "Temp";
	}
}