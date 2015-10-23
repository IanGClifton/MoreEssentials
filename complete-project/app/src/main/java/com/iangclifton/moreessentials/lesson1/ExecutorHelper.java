package com.iangclifton.moreessentials.lesson1;

import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Ian G. Clifton
 */
public class ExecutorHelper {

    private static final ExecutorHelper INSTANCE = new ExecutorHelper();

    private ExecutorService mExecutorService;

    private ExecutorHelper() {
        // Private so no one else calls this
    }

    public static ExecutorHelper getInstance() {
        return INSTANCE;
    }

    public void execute(Runnable runnable) {
        if (mExecutorService == null) {
            mExecutorService = Executors.newSingleThreadExecutor();
        }
        mExecutorService.execute(runnable);
    }

    public static void staticExecute(Runnable runnable) {
        if (INSTANCE.mExecutorService == null) {
            INSTANCE.mExecutorService = Executors.newSingleThreadExecutor();
        }
        INSTANCE.mExecutorService.execute(runnable);
    }

    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }
}
