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

  Payhere payhere = new Payhere(
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
                  String reps = await payhere.makePayment();
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