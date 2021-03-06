package aliyun;

import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.application.App;
import com.bean.ImageInfo;
import com.constant.Constant;

import java.util.ArrayList;
import java.util.List;

import tool.ShowToast;

/**
 * 阿里云oss封装
 * Created by Administrator on 2017/1/8.
 */

public class AliOss {
    private  int count;//上传次数
    private List<ImageInfo> results=new ArrayList<>();
    //上传监听接口
    public interface UploadListener {
        //上传成功
        void success(List<ImageInfo> results);
        void fail();
    }
    private OSS oss;
    private String endpoint = "oss-cn-shanghai.aliyuncs.com";
    private String bucketName="clocle";
    private static AliOss instance=new AliOss();
    OSSAsyncTask task;
    //单例模式
    public static AliOss getAliOss(){
        return instance;
    }
    private  AliOss(){


// 明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考后面的`访问控制`章节
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider("LTAIyaWByvISPPPv", "EM007U4vfjwviDGSjKwnPPePUF0AH8");

        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSSLog.enableLog();
        oss = new OSSClient(App.getContext(), endpoint, credentialProvider,conf);
    }

public void startUploadTasks(final List<ImageInfo> o, final UploadListener listener){
    count=0;
//for (ImageInfo info:o){
    final PutObjectRequest  put = new PutObjectRequest(bucketName, o.get(0).name+".png", o.get(0).url);
    task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
        @Override
        public void onSuccess(PutObjectRequest request, PutObjectResult result) {
            count++;
            results.add(formAliPath(put));

            ShowToast.showToast(App.getContext(),"成功");
            /*if(count==o.size()){
                //已结上传完成
                listener.success(results);
            }*/
            Log.d("PutObject", "UploadSuccess");
        }

        @Override
        public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
            //ShowToast.showToast(App.getContext(),"上传失败");
           // listener.fail();
            // 请求异常
            if (clientExcepion != null) {
                // 本地异常如网络异常等
                clientExcepion.printStackTrace();
            }
            if (serviceException != null) {

                // 服务异常
                Log.e("ErrorCode", serviceException.getErrorCode());
                Log.e("RequestId", serviceException.getRequestId());
                Log.e("HostId", serviceException.getHostId());
                Log.e("RawMessage", serviceException.getRawMessage());
            }
        }
    });
//}
}

    /**
     * 单张图片异步上传
     */
    public void UploadToOssAsyn(Object o){
        // 构造上传请求，文件名包括后缀名


            ImageInfo info = (ImageInfo) o;
        PutObjectRequest  put = new PutObjectRequest(bucketName, info.name+".png", info.url);

        //PutObjectRequest put = new PutObjectRequest(bucketName, info.name+".png", filepath);

// 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                Log.d("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
            }
        });

        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.d("PutObject", "UploadSuccess");
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                ShowToast.showToast(App.getContext(),"上传失败");
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });

// task.cancel(); // 可以取消任务

// task.waitUntilFinished(); // 可以等待直到任务完成
    }

    /**
     * 取消任务
     */
    public void cancleTasks() {

        if (!task.isCompleted()) {
            task.cancel();
        }
    }
    /**
     * 同步图片上传
     * 三失败返回失败code，
     * 成功返回OSS外链路径
     */
    public String UploadToOssSync(String filename,String filePath){
    // 构造上传请求
    PutObjectRequest put = new PutObjectRequest(bucketName, filename, filePath);
// 文件元信息的设置是可选的
//ObjectMetadata metadata = new ObjectMetadata();
        //metadata.set("Ossfilepath","http://"+endpoint+filename);//oss上文件路径
// metadata.setContentType("application/octet-stream"); // 设置content-type
// metadata.setContentMD5(BinaryUtil.calculateBase64Md5(uploadFilePath)); // 校验MD5
// put.setMetadata(metadata);
    try {
        PutObjectResult putResult = oss.putObject(put);
return "http://"+endpoint+filename;
    } catch (ClientException e) {
        // 本地异常如网络异常等
        e.printStackTrace();
        return Constant.FAIL;
    } catch (ServiceException e) {
        // 服务异常
        Log.e("RequestId", e.getRequestId());
        Log.e("ErrorCode", e.getErrorCode());
        Log.e("HostId", e.getHostId());
        Log.e("RawMessage", e.getRawMessage());
        return Constant.FAIL;
    }


}
    /**
     * 拼接远程访问地址
     *
     * @param putObjectRequest
     * @return
     */
    private ImageInfo formAliPath(PutObjectRequest putObjectRequest) {
        ImageInfo info=new ImageInfo();
        info.url="http://" + putObjectRequest.getBucketName() + "." + endpoint + "/" + putObjectRequest.getObjectKey();
   return info;
    }
}
