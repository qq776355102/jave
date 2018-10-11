# jave
音频视频格式转换工具,适用于window,linux,macos系统

#
音频转换demo
#
写在前面
#
Java音频视频编码器，本文实例将任何音频格式转换为MP3格式

您还可以调整音频频大小，更改其大小和比例等

本文实例实用于任何window,macos,linux操作系统

使用此项目只需要,导入jar包,简单易操作.参数只有两个:

①一个File类型的source对象:

         //File source = new File("C:\\Users\\source.m4a");
         File source = new File("file path source");

②一个File类型target对象,
         // File target = new File("C:\\Users\\target.mp3");
         File target = new File("file path target");

Example
完整jar项目，及更多demo下载地址：https://download.csdn.net/download/weixin_39842528/10616527

public class ConvertingAnyAudioToMp3_Example{ 
 
 
 
         //测试demo|
        //使用此jar只需要,源文件路径和要生成目标文件的路径
 
       public static void main(String[] args) {
        
 
        ConvertingAnyAudioToMp3_Example  cAATM  =  new ConvertingAnyAudioToMp3_Example();
         //C:\\Users\\source.m4a
         File source = new File("file path source");
         //C:\\Users\\target.mp3
         File target = new File("file path target");
         boolean b = cAATM .ConvertingAnyAudioToMp3WithAProgressListener(source,target);
         System.out.println(b);
  
 
 }
    //以下代码不用改动,直接使用
 
   /**
     * 第一个参数source表示要解码的源文件。
     * 
     * 第二个参数target是将要创建和编码的目标文件。
     * 
     * 
     * 请注意，此方法是阻塞的：只有在转码操作完成（或失败）后，该方法才会返回
     * 
     * @return boolean
     */
    public boolean ConvertingAnyAudioToMp3WithAProgressListener(java.io.File source, java.io.File target) {
        
        ConvertProgressListener listener = new ConvertProgressListener();
 
        boolean succeeded = true;
 
        try {
            /*
             * 自定义 TODO
             * 
             */
//            File source = new File("file path source");
            /*
             * 自定义 TODO
             * 
             */
//            File target = new File("file path target");
 
            // Audio Attributes/音频编码属性
            AudioAttributes audio = new AudioAttributes();
            /*
             * 它设置将用于音频流转码的编解码器的名称。您必须从当前Encoder实例的getAudioEncoders（）方法返回的列表中选择一个值。否则，
             * 您可以传递AudioAttributes.DIRECT_STREAM_COPY特殊值，该值需要源文件中原始音频流的副本。
             */
            audio.setCodec("libmp3lame");
            /*
             * 它设置新重新编码的音频流的比特率值。如果未设置比特率值，编码器将选择默认值。该值应以每秒位数表示。例如，如果你想要128 kb /
             * s比特率，你应该调用setBitRate（new Integer（128000））。
             */
            audio.setBitRate(128000);
            /* 它设置将在重新编码的音频流中使用的音频通道的数量（1 =单声道，2 =立体声）。如果未设置通道值，编码器将选择默认值。 */
            audio.setChannels(2);
            /*
             * 它设置新重新编码的音频流的采样率。如果未设置采样率值，编码器将选择默认值。该值应以赫兹表示。例如，如果您想要类似CD的44100
             * Hz采样率，则应调用setSamplingRate（new Integer（44100））。
             */
            audio.setSamplingRate(44100);
            /* 可以调用此方法来改变音频流的音量。值256表示没有音量变化。因此，小于256的值是音量减小，而大于256的值将增加音频流的音量。 */
            audio.setVolume(new Integer(256));
 
            // Encoding attributes/编码属性
            EncodingAttributes attrs = new EncodingAttributes();
            /*
             * 它设置将用于新编码文件的流容器的格式。给定参数表示格式名称。
             * 编码格式名称有效且仅在它出现在正在使用的Encoder实例的getSupportedEncodingFormats（）方法返回的列表中时才受支持。
             */
            attrs.setFormat("mp3");
            /* 它设置音频编码属性。如果从未调用过新的EncodingAttributes实例，或者给定参数为null，则编码文件中不会包含任何音频流 */
            attrs.setAudioAttributes(audio);
            /*
             * 它为转码操作设置偏移量。源文件将从其开始的偏移秒开始重新编码。例如，如果您想剪切源文件的前五秒，
             * 则应在传递给编码器的EncodingAttributes对象上调用setOffset（5）。
             */
            // attrs.setOffset(5F);
            /*
             * 它设置转码操作的持续时间。只有源的持续时间秒才会在目标文件中重新编码。例如，如果您想从源中提取和转码30秒的一部分，
             * 则应在传递给编码器的EncodingAttributes对象上调用setDuration（30）
             */
            // attrs.setDuration(30F);
 
            // Encode/编码
            Encoder encoder = new Encoder();
            encoder.encode(new MultimediaObject(source), target, attrs, listener);
 
        } catch (Exception ex) {
            ex.printStackTrace();
            succeeded = false;
        }
        return succeeded;
    }
 
    public class ConvertProgressListener implements EncoderProgressListener {
 
        /*编码器在分析源文件后调用此方法。该信息参数是实例 ws.schild.jave.MultimediaInfo类，它代表了有关源音频和视频流及其容器的信息。*/
        public void sourceInfo(MultimediaInfo info) {
            // TODO Auto-generated method stub
 
        }
        /*每次完成编码操作的进度时，编码器调用该方法。所述permil参数是表示通过当前操作到达点的值和它的范围是从0（操作刚开始）到1000（操作完成）*/
        public void progress(int permil) {
            double progress = permil / 1000.00;                                    
            System.out.println(progress); 
 
        }
 
        /* 编码器调用该方法以通知关于代码转换操作的消息（通常消息是警告）。 */
        public void message(String message) {
            // TODO Auto-generated method stub
 
        }
 
    }
}
如果你对此比较感兴趣你可以联系

微信：momo1106714071
