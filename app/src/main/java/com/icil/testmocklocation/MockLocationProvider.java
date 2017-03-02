package com.icil.testmocklocation;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.SystemClock;

/**
 * Created by BRB_LAB on 2017-02-03.
 * Packaged : com.icil.mocklocation
 * Web : http://myandroidarchive.tistory.com
 */

class MockLocationProvider {
    String providerName;
    Context mctx;

    public MockLocationProvider(String name, Context ctx) {
        providerName = name;
        mctx = ctx;

        LocationManager lm = (LocationManager) ctx.getSystemService(
                Context.LOCATION_SERVICE);
        lm.addTestProvider(providerName, true, true, true, true, true,
                true, true, Criteria.NO_REQUIREMENT, Criteria.ACCURACY_FINE);
        lm.setTestProviderEnabled(providerName, true);
    }

    public void pushLocation(double lat, double lon, double alt) {
        try {
            LocationManager lm = (LocationManager) mctx.getSystemService(
                    Context.LOCATION_SERVICE);

            Location mockLocation = new Location(providerName);
            long currentTime = System.currentTimeMillis();
            mockLocation.setLatitude(lat);
            mockLocation.setLongitude(lon);
            mockLocation.setAltitude(alt);
            mockLocation.setTime(currentTime);
            mockLocation.setAccuracy(1.0f);
            mockLocation.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());

            lm.setTestProviderStatus(providerName, LocationProvider.AVAILABLE, mockLocation.getExtras(), currentTime);
            lm.setTestProviderLocation(providerName, mockLocation);
        } catch(RuntimeException e){
            e.printStackTrace();
        }
    }

    public void shutdown() {
        LocationManager lm = (LocationManager) mctx.getSystemService(
                Context.LOCATION_SERVICE);
        lm.removeTestProvider(providerName);
    }
}