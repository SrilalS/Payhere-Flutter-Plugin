import 'package:flutter/material.dart';
import 'package:payhere/payhere.dart';

void main() {
  runApp(PayhereExample());
}

class PayhereExample extends StatefulWidget {
  @override
  _PayhereExampleState createState() => _PayhereExampleState();
}

class _PayhereExampleState extends State<PayhereExample> {
  String response = "Click the Above Button";

  Payhere payhere = new Payhere();
  OneTimePayment oneTimePayment = new OneTimePayment(
      merchantID: "1213586",
      merchantSecret: "48WrQKBwKts4PbEy5gCd878QfqpdU2Zzl4PbG9xxs3d8",
      currency: "LKR",
      totalamount: 100,
      orderID: "XTX",
      itemDesc: "decribe thy item",
      cM1: "CM1",
      cM2: "CM2",
      fName: "FName",
      lName: "LName",
      email: "Email",
      phone: "Phone",
      address: "Address",
      city: "Colombo",
      country: "Sri Lanka",
      //these are optional values as stated in the payhere android sdk.
      deliveryAddress: "deliveryAddress",
      deliveryCity: "deliveryAddress",
      deliveryCountry: "deliveryAddress");
  RecurringPayment recurringPayment = new RecurringPayment(
      "Merchant ID",
      "Merchant Secret",
      "LKR",
      120,
      "o6ae978a1",
      "testsub",
      "cM1",
      "cM2",
      "fName",
      "lName",
      "email",
      "+94771234567",
      "address",
      "Colombo",
      "Sri Lanka",
      "1 Month",
      "Forever",
      2);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Payhere Example App'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              ElevatedButton(
                onPressed: () async {

                  String reps = await payhere.makeOneTimePayment(
                      oneTimePayment,
                      notifyurl: 'localhost',
                      returnurl: 'localhost',
                      cancelnurl: 'localhost');
                  setState(() {
                    response = reps;
                  });
                },
                child: Text("One Time Payment SANDBOX"),
              ),
              Text(response)
            ],
          ),
        ),
      ),
    );
  }
}
