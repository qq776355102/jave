package audio;


import java.io.File;

import ws.schild.jave.AudioAttributes;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderProgressListener;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.MultimediaInfo;
import ws.schild.jave.MultimediaObject;

/**
 * @author mc
 * @since 2018��8��21��
 * 	
 */
public class ConvertingAnyAudioToMp3_Example2{
	public static void main(String[] args) {
		ConvertingAnyAudioToMp3_Example2 convertingAnyAudioToMp3_Example = new ConvertingAnyAudioToMp3_Example2();
		File source = new File("file path source");
		File target = new File("file path target");
		boolean b = convertingAnyAudioToMp3_Example.ConvertingAnyAudioToMp3WithAProgressListener(source,target);
		System.out.println(b);
	}

	/**
	 * ��һ������source��ʾҪ�����Դ�ļ���
	 * 
	 * �ڶ�������target�ǽ�Ҫ�����ͱ����Ŀ���ļ���
	 * 
	 * 
	 * ��ע�⣬�˷����������ģ�ֻ����ת�������ɣ���ʧ�ܣ��󣬸÷����Ż᷵��
	 * 
	 * @return boolean
	 */
	public boolean ConvertingAnyAudioToMp3WithAProgressListener(java.io.File source, java.io.File target) {
		
		ConvertProgressListener listener = new ConvertProgressListener();

		boolean succeeded = true;

		try {
			/*
			 * �Զ��� TODO
			 * 
			 */
//			File source = new File("file path source");
			/*
			 * �Զ��� TODO
			 * 
			 */
//			File target = new File("file path target");

			// Audio Attributes/��Ƶ��������
			AudioAttributes audio = new AudioAttributes();
			/*
			 * �����ý�������Ƶ��ת��ı�����������ơ�������ӵ�ǰEncoderʵ����getAudioEncoders�����������ص��б���ѡ��һ��ֵ������
			 * �����Դ���AudioAttributes.DIRECT_STREAM_COPY����ֵ����ֵ��ҪԴ�ļ���ԭʼ��Ƶ���ĸ�����
			 */
			audio.setCodec("libmp3lame");
			/*
			 * �����������±������Ƶ���ı�����ֵ�����δ���ñ�����ֵ����������ѡ��Ĭ��ֵ����ֵӦ��ÿ��λ����ʾ�����磬�������Ҫ128 kb /
			 * s�����ʣ���Ӧ�õ���setBitRate��new Integer��128000������
			 */
			audio.setBitRate(128000);
			/* �����ý������±������Ƶ����ʹ�õ���Ƶͨ����������1 =��������2 =�������������δ����ͨ��ֵ����������ѡ��Ĭ��ֵ�� */
			audio.setChannels(2);
			/*
			 * �����������±������Ƶ���Ĳ����ʡ����δ���ò�����ֵ����������ѡ��Ĭ��ֵ����ֵӦ�Ժ��ȱ�ʾ�����磬�������Ҫ����CD��44100
			 * Hz�����ʣ���Ӧ����setSamplingRate��new Integer��44100������
			 */
			audio.setSamplingRate(44100);
			/* ���Ե��ô˷������ı���Ƶ����������ֵ256��ʾû�������仯����ˣ�С��256��ֵ��������С��������256��ֵ��������Ƶ���������� */
			audio.setVolume(new Integer(256));

			// Encoding attributes/��������
			EncodingAttributes attrs = new EncodingAttributes();
			/*
			 * �����ý������±����ļ����������ĸ�ʽ������������ʾ��ʽ���ơ�
			 * �����ʽ������Ч�ҽ���������������ʹ�õ�Encoderʵ����getSupportedEncodingFormats�����������ص��б���ʱ����֧�֡�
			 */
			attrs.setFormat("mp3");
			/* ��������Ƶ�������ԡ������δ���ù��µ�EncodingAttributesʵ�������߸�������Ϊnull��������ļ��в�������κ���Ƶ�� */
			attrs.setAudioAttributes(audio);
			/*
			 * ��Ϊת���������ƫ������Դ�ļ������俪ʼ��ƫ���뿪ʼ���±��롣���磬����������Դ�ļ���ǰ���룬
			 * ��Ӧ�ڴ��ݸ���������EncodingAttributes�����ϵ���setOffset��5����
			 */
			// attrs.setOffset(5F);
			/*
			 * ������ת������ĳ���ʱ�䡣ֻ��Դ�ĳ���ʱ����Ż���Ŀ���ļ������±��롣���磬��������Դ����ȡ��ת��30���һ���֣�
			 * ��Ӧ�ڴ��ݸ���������EncodingAttributes�����ϵ���setDuration��30��
			 */
			// attrs.setDuration(30F);

			// Encode/����
			Encoder encoder = new Encoder();
			encoder.encode(new MultimediaObject(source), target, attrs, listener);

		} catch (Exception ex) {
			ex.printStackTrace();
			succeeded = false;
		}
		return succeeded;
	}

	public class ConvertProgressListener implements EncoderProgressListener {

		/*�������ڷ���Դ�ļ�����ô˷���������Ϣ������ʵ�� ws.schild.jave.MultimediaInfo�࣬���������й�Դ��Ƶ����Ƶ��������������Ϣ��*/
		public void sourceInfo(MultimediaInfo info) {
			// TODO Auto-generated method stub

		}
		/*ÿ����ɱ�������Ľ���ʱ�����������ø÷���������permil�����Ǳ�ʾͨ����ǰ����������ֵ�����ķ�Χ�Ǵ�0�������տ�ʼ����1000��������ɣ�*/
		public void progress(int permil) {
			double progress = permil / 1000.00;                                    
			System.out.println(progress); 

		}

		/* ���������ø÷�����֪ͨ���ڴ���ת����������Ϣ��ͨ����Ϣ�Ǿ��棩�� */
		public void message(String message) {
			// TODO Auto-generated method stub

		}

	}
}

/*
 * Transcoding failures/ת��ʧ�� ��Ȼ��ת��������ܻ�ʧ�ܡ�Ȼ��encode���������������쳣�����ݷ�����������쳣��������֮һ��
 * 
 * java.lang.IllegalArgumentException ת�������δ��ʼ����Ϊ���ݸ��������ı��������ѱ�ʶ��Ϊ��Ч��ͨ����
 * �������������EncodingAttributesʵ��ѯ��û����Ƶ��û����Ƶ���������ı���ʱ���ᷢ�����������
 * AudioAttributes��VideoAttribues���Զ�Ϊnull��δ���ã���
 * ws.schild.jave.InputFormatException
 * �޷�����Դ�ļ�������������֧��Դ�ļ���������Ƶ����ʽ����Ƶ����ʽʱ���ᷢ����������������Լ��֧�ֵ������Ͳ���Ľ�������
 * ���ñ���������getSupportedDecodingFormats������getAudioDecoders������getVideoDecoders������
 * ws.schild.jave.EncoderException
 * �����ڲ�������ת������в���ʧ�ܡ���Ӧ�ü���쳣��Ϣ�����һ�����ʹ��EncoderProgressListenerʵ�������������������κ���Ϣ��
 */