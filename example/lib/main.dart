import 'package:flutter/material.dart';
import 'package:payhere/payhere.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String response = "NOT YET";

  OneTimePayment oneTimePayment = new OneTimePayment(
      "1213586",
      "4pH81nUGhXF8hivSd4bAgc4ZGRjX4m7Rh8MOFpE8EZTL",
      "LKR",
      250,
      "12566548",
      "itemDesc",
      "cM1",
      "cM2",
      "fName",
      "lName",
      "email",
      "+94771234567",
      "address",
      "Colombo",
      "Sri Lanka",
      "deliveryAddress",
      "Colombo",
      "Sri Lanka",
      2);

        RecurringPayment recurringPayment = new RecurringPayment(
      "1213586",
      "4pH81nUGhXF8hivSd4bAgc4ZGRjX4m7Rh8MOFpE8EZTL",
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



  Payhere payhere = new Payhere();

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
              RaisedButton(
                onPressed: () async {
                  //String reps = await payhere.makeOneTimePayment(oneTimePayment);
                  String reps = await payhere.makeRecurringPayment(recurringPayment);
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
