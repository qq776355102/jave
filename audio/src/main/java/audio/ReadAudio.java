package audio;

import java.io.File;

import ws.schild.jave.MultimediaInfo;
import ws.schild.jave.MultimediaObject;

public class ReadAudio {

	
	public static void main(String[] args)  {
		File file = new File("");
		MultimediaObject m = new MultimediaObject(file);
		try {
			MultimediaInfo info = m.getInfo();
			long ls = info.getDuration();
			System.out.println("����Ƶʱ��Ϊ:" + ls / 60000 + "��" + ls / 1000 + "�룡");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
	}
}
