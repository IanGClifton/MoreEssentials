package com.iangclifton.moreessentials.lesson8;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.renderscript.ScriptIntrinsicConvolve3x3;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.iangclifton.moreessentials.R;

/**
 * Fragment for running the Lesson 8 examples.
 *
 * This Fragment demonstrates using RenderScript, but we took a few
 * shortcuts. It is a good idea to put your RenderScript work on a background
 * thread since forEach can block until the GPU is done. It's also worth
 * noting that there is no guarantee that the code runs on the GPU (in most
 * modern devices it should, but execution can fall to the CPU if a device
 * has a GPU which doesn't support specific operations).
 *
 * Another shortcut we took for simplicity is processing an image in the
 * XHDPI drawable folder. This means that on devices that are have a higher
 * density than XHDPI, Android is scaling the image and then running the
 * RenderScript on that scaled image. It can be more efficient to run the
 * RenderScript on the image and then scale it up (so that you're operating
 * on fewer pixels). Of course, if you are scaling the imgage down, then
 * scaling it before processing it will typically be faster.
 *
 * You should also keep the Bitmap objects around for configuration changes,
 * when it makes sense. If rotating the device does not change the image,
 * keeping the processed image around saves the device some work.
 *
 * If you're looking for new matrices for convolutions, just do a Google
 * search for "convolution matrix" and you should see many examples. Some
 * image manipulation software also allows you to specify your own, such
 * as GIMP, which can be easier for testing different values before coding
 * them into the app.
 *
 * @author Ian G. Clifton
 */
public class Lesson8Fragment extends Fragment {

    private static final float[] EMBOSS_MATRIX = {
            -2, -1,  0,
            -1,  1,  1,
             0,  1,  2,
    };

    private static final float[] SHARPEN_MATRIX = {
             0, -1,  0,
            -1,  5, -1,
             0, -1,  0,
    };

    public Lesson8Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_lesson8, container, false);

        ImageView imageView1 = (ImageView) rootView.findViewById(R.id.image1);
        ImageView imageView2 = (ImageView) rootView.findViewById(R.id.image2);
        ImageView imageView3 = (ImageView) rootView.findViewById(R.id.image3);


        Bitmap inputBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test_image);
        imageView1.setImageBitmap(inputBitmap);
//        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap.getWidth(), inputBitmap.getHeight(), inputBitmap.getConfig());
//
//        RenderScript rs = RenderScript.create(getActivity());
//        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));;
//        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
//        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
//        theIntrinsic.setRadius(5f);
//        theIntrinsic.setInput(tmpIn);
//        // forEach can block
//        theIntrinsic.forEach(tmpOut);
//        tmpOut.copyTo(outputBitmap);
//
//        imageView2.setImageBitmap(outputBitmap);


        // Convolution
        Bitmap convOutputBitmap = Bitmap.createBitmap(inputBitmap.getWidth(), inputBitmap.getHeight(), inputBitmap.getConfig());

        RenderScript rs = RenderScript.create(getActivity());
        ScriptIntrinsicConvolve3x3 convIntrinsic = ScriptIntrinsicConvolve3x3.create(rs, Element.U8_4(rs));;
        Allocation convTmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation convTmpOut = Allocation.createFromBitmap(rs, convOutputBitmap);
        convIntrinsic.setCoefficients(SHARPEN_MATRIX);
        convIntrinsic.setInput(convTmpIn);
        // forEach can block
        convIntrinsic.forEach(convTmpOut);
        convTmpOut.copyTo(convOutputBitmap);

        imageView3.setImageBitmap(convOutputBitmap);


        return rootView;
    }


}
