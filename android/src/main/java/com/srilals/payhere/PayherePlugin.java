package com.srilals.payhere;

import android.util.Log;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * PayherePlugin for Flutter SDK
 */
public class PayherePlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {

    private PayhereOTP payhereOTP;
    private ActivityPluginBinding pluginBinding;
    private MethodChannel channel;


    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "Payhere");
        channel.setMethodCallHandler(this);
    }

    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "Payhere");
        channel.setMethodCallHandler(new PayherePlugin());
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        if (call.method.equals("OTPPAY")) {
            Log.d("TAG", "OTP PAY Call Received");
            String MID = call.argument("MID").toString();
            String MSecret = call.argument("MSecret").toString();
            String Currency = call.argument("Currency").toString();
            double Amount = Double.parseDouble(call.argument("Amount").toString());
            String OrderID = call.argument("OrderID").toString();
            String ItemDesc = call.argument("ItemDesc").toString();
            String CM1 = call.argument("CM1").toString();
            String CM2 = call.argument("CM2").toString();
            String FName = call.argument("FName").toString();
            String LName = call.argument("LName").toString();
            String Email = call.argument("EMail").toString();
            String Phone = call.argument("Phone").toString();
            String Address = call.argument("Address").toString();
            String City = call.argument("City").toString();
            String Country = call.argument("Country").toString();
            String DeliveryAddress = call.argument("DeliveryAddress").toString();
            String DeliveryCity = call.argument("DeliveryCity").toString();
            String DeliveryCountry = call.argument("DeliveryCountry").toString();
            int Quantity = Integer.parseInt(call.argument("Quantity").toString());
            String MODE = call.argument("MODE").toString();

            payhereOTP.oneTimePayment(result, MID, MSecret, Currency, Amount, OrderID, ItemDesc, CM1, CM2, FName, LName, Email, Phone, Address, City, Country, DeliveryAddress, DeliveryCity, DeliveryCountry, Quantity, MODE);

        } else if (call.method.equals("RPPAY")) {
            Log.d("TAG", "RP PAY Call Received");
            String MID = call.argument("MID").toString();
            String MSecret = call.argument("MSecret").toString();
            String Currency = call.argument("Currency").toString();
            double Amount = Double.parseDouble(call.argument("Amount").toString());

            String Recurrence = call.argument("Recurrence").toString();
            String Duration = call.argument("Duration").toString();

            String OrderID = call.argument("OrderID").toString();
            String ItemDesc = call.argument("ItemDesc").toString();
            String CM1 = call.argument("CM1").toString();
            String CM2 = call.argument("CM2").toString();
            String FName = call.argument("FName").toString();
            String LName = call.argument("LName").toString();
            String Email = call.argument("EMail").toString();
            String Phone = call.argument("Phone").toString();
            String Address = call.argument("Address").toString();
            String City = call.argument("City").toString();
            String Country = call.argument("Country").toString();

            double StartUpFee = Double.parseDouble(call.argument("StartUpFee").toString());

            String MODE = call.argument("MODE").toString();

            payhereOTP.recurringPayment(result, MID, MSecret, Currency, Amount, OrderID, ItemDesc, CM1, CM2, FName, LName, Email, Phone, Address, City, Country, Recurrence, Duration, StartUpFee, MODE);
        } else {
            result.notImplemented();
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    }

    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
        this.payhereOTP = new PayhereOTP(binding.getActivity(), channel);
        this.pluginBinding = binding;
        binding.addActivityResultListener(payhereOTP);
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {
        onDetachedFromActivity();
    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
        onAttachedToActivity(binding);
    }

    @Override
    public void onDetachedFromActivity() {
        pluginBinding.removeActivityResultListener(payhereOTP);
        pluginBinding = null;
    }

}
