import 'dart:async';

import 'package:flutter/services.dart';

class Payhere {
  /// Declare method channel
  static const pF = MethodChannel('Payhere');
  String result = "FAILURE";

  /// OneTimePayemnt function. this will return response String.
  Future<String> makeOneTimePayment(OneTimePayment oneTimePayment,
      {String? returnurl, String? cancelnurl, String? notifyurl}) async {
    await pF.invokeMethod("OTPPAY", oneTimePayment.toJson());
    await waitForResponse();
    return result;
  }

  /// RecurringPayemnt function. this will return response String.
  Future<String> makeRecurringPayment(RecurringPayment recurringPayment) async {
    await pF.invokeMethod("RPPAY", recurringPayment.toJson());
    await waitForResponse();
    return result;
  }

  /// This will wait for the response from Payhere native sdk.
  Future waitForResponse() {
    Completer completer = Completer();
    pF.setMethodCallHandler((call) async {
      result = call.arguments.toString();
      completer.complete();
    });
    return completer.future;
  }
}

/// Main OneTimePayment Class
class OneTimePayment {
  /// Put the Merchant ID here.
  String? merchantID;

  /// Put the Merchant secret here.
  String? merchantSecret;

  /// You can't test the nofify url on local host. you WILL need a server on the web.
  String? notifyUrl;
  String? currency;
  double? amount;
  String? orderID;
  String? itemDesc;
  String? cM1;
  String? cM2;
  String? fName;
  String? lName;
  String? email;
  String? phone;
  String? address;
  String? city;
  String? country;

  /// Optional Parameters as stated in Payhere Documentation.
  String? deliveryAddress;
  String? deliveryCity;
  String? deliveryCountry;

  /// By default payment mode is in Sandbox mode.
  String mode = "SANDBOX";

  // Set the payement mode to Sandbox Mode
  void setSandBoxMode() {
    this.mode = "SANDBOX";
  }

  // Set the payement mode to Production Mode Mode
  void setProductionMode() {
    this.mode = "PRODUCTION";
  }

  /// OTP Object Creation.
  OneTimePayment({
    String? merchantID,
    String? merchantSecret,
    String? notifyUrl,
    String? currency,
    double? totalamount,
    String? orderID,
    String? itemDesc,
    String? cM1,
    String? cM2,
    String? fName,
    String? lName,
    String? email,
    String? phone,
    String? address,
    String? city,
    String? country,
    String? deliveryAddress,
    String? deliveryCity,
    String? deliveryCountry,
  }) {
    this.merchantID = merchantID;
    this.merchantSecret = merchantSecret;
    this.notifyUrl = notifyUrl;
    this.currency = currency;
    this.amount = totalamount;
    this.orderID = orderID;
    this.itemDesc = itemDesc;
    this.cM1 = cM1;
    this.cM2 = cM2;
    this.fName = fName;
    this.lName = lName;
    this.email = email;
    this.phone = phone;
    this.address = address;
    this.city = city;
    this.country = country;
    this.deliveryAddress = deliveryAddress;
    this.deliveryCity = deliveryCity;
    this.deliveryCountry = deliveryCountry;
  }

  /// Convert the pyament object to json.
  Map toJson() => {
        "MID": this.merchantID,
        "MSecret": this.merchantSecret,
        "NotifyUrl": this.notifyUrl,
        "Currency": this.currency,
        "Amount": this.amount,
        "OrderID": this.orderID,
        "ItemDesc": this.itemDesc,
        "CM1": this.cM1,
        "CM2": this.cM2,
        "FName": this.fName,
        "LName": this.lName,
        "EMail": this.email,
        "Phone": this.phone,
        "Address": this.address,
        "City": this.city,
        "Country": this.country,
        "DeliveryAddress": this.deliveryAddress,
        "DeliveryCity": this.deliveryCity,
        "DeliveryCountry": this.deliveryCountry,
        "MODE": this.mode
      };
}

/// Main OneTimePayment Class.
/// Cannot test this as the sandbx isn't allowing to use Recurring Payments.
class RecurringPayment {
  /// Puts the Merchant ID here.
  String? _merchantID;

  /// Puts the Merchant secret here
  String? _merchantSecret;

  /// You can't test the nofify url on local host. you WILL need a server on the web.
  String? _notifyUrl;

  /// Currency Type ex: LKR, USD ...
  String? _currency;
  double? _amount;
  String? _orderID;
  String? _itemDesc;
  String? _cM1;
  String? _cM2;
  String? _fName;
  String? _lName;
  String? _email;
  String? _phone;
  String? _address;
  String? _city;
  String? _country;

  String? _recurrence;
  String? _duration;
  double? _startUpFee;

  /// By default payment mode is in Sandbox mode.
  String _mode = "SANDBOX";

  // Set the payement mode to Sandbox Mode
  void setSandBoxMode() {
    this._mode = "SANDBOX";
  }

  // Set the payement mode to Production Mode
  void setProductionMode() {
    this._mode = "PRODUCTION";
  }

  /// RP Object is Created.
  RecurringPayment(
      String merchantID,
      String merchantSecret,
      String notifyUrl,
      String currency,
      double amount,
      String orderID,
      String itemDesc,
      String cM1,
      String cM2,
      String fName,
      String lName,
      String email,
      String phone,
      String address,
      String city,
      String country,
      String recurrence,
      String duration,
      double startUpFee) {
    this._merchantID = merchantID;
    this._merchantSecret = merchantSecret;
    this._notifyUrl = notifyUrl;
    this._currency = currency;
    this._amount = amount;
    this._orderID = orderID;
    this._itemDesc = itemDesc;
    this._cM1 = cM1;
    this._cM2 = cM2;
    this._fName = fName;
    this._lName = lName;
    this._email = email;
    this._phone = phone;
    this._address = address;
    this._city = city;
    this._country = country;
    this._recurrence = recurrence;
    this._duration = duration;
    this._startUpFee = startUpFee;
  }

  /// Convert the RP Object to JSON.
  Map toJson() => {
        "MID": this._merchantID,
        "MSecret": this._merchantSecret,
        "NotifyUrl": this._notifyUrl,
        "Currency": this._currency,
        "Amount": this._amount,
        "OrderID": this._orderID,
        "ItemDesc": this._itemDesc,
        "CM1": this._cM1,
        "CM2": this._cM2,
        "FName": this._fName,
        "LName": this._lName,
        "EMail": this._email,
        "Phone": this._phone,
        "Address": this._address,
        "City": this._city,
        "Country": this._country,
        "Recurrence": this._recurrence,
        "Duration": this._duration,
        "StartUpFee": this._startUpFee,
        "MODE": this._mode
      };
}
