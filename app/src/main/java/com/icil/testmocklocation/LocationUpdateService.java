package com.icil.testmocklocation;

import android.app.IntentService;
import android.content.Intent;
import android.location.LocationManager;

/**
 * Created by BRB_LAB on 2017-02-03.
 * Packaged : com.icil.mocklocationbackground
 * Web : http://myandroidarchive.tistory.com
 */

public class LocationUpdateService extends IntentService{
    private MockLocationProvider mMock;
    private double[] mLattitude = {37.575573,37.5753944,37.5752158,37.5750372,37.5748586,37.57468,37.5745014,37.5743228,37.5741442,37.5739656,37.573787,37.5736084,37.5734298,37.5732512,37.5730726,37.572894,37.5727154,37.5725368,37.5723582,37.5721796,37.572001,37.5718224,37.5716438,37.5714652,37.5712866,37.571108,37.5709294,37.5707508,37.5705722,37.5703936,37.570215};
    private double mLongditude = 126.976923;
    private double mAltitude = 100;
    public LocationUpdateService() {
        super("LocationUpdate");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mMock = new MockLocationProvider(LocationManager.NETWORK_PROVIDER, this);

        mLocationUpdate.run();
    }

    Runnable mLocationUpdate = new Runnable() {
        @Override
        public void run() {
            for(int idx = 0; ; idx++){
                try {
                    mMock.pushLocation(mLattitude[idx % mLattitude.length], mLongditude, mAltitude);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMock.shutdown();
    }
}
