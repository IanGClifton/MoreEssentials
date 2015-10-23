package com.iangclifton.moreessentials.lesson1;

import android.os.Handler;
import android.os.Message;

/**
 * @author Ian G. Clifton
 */
public abstract class ResultHandler<Result> extends Handler {

    private static final int SUCCESS = 1;
    private static final int FAILURE = 2;

    public void sendSuccess(Result result) {
        // Create a Message and add to the message queue
        // BAD: Message message = new Message();
        Message message = obtainMessage(SUCCESS, result);
        sendMessage(message);
    }

    public void sendFailure() {
        Message message = obtainMessage(FAILURE);
        sendMessage(message);
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SUCCESS:
                onSuccess((Result) msg.obj);
                break;
            case FAILURE:
                onFailure();
                break;
        }
    }

    public abstract void onFailure();

    public abstract void onSuccess(Result result);
}
