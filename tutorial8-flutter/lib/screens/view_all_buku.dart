import 'dart:convert';
import 'package:bacabaca_mobile/model/BukuResponse.dart';
import 'package:bacabaca_mobile/utils/reusable_widget.dart';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:jwt_decoder/jwt_decoder.dart';
import 'package:shared_preferences/shared_preferences.dart';

class BukuScreen extends StatefulWidget {
  static const routeName = '/appointment/list';
  const BukuScreen({super.key});

  @override
  State<BukuScreen> createState() => _BukuScreenState();
}

class _BukuScreenState extends State<BukuScreen> {
  Future<String> getJwt() async {
      SharedPreferences prefs = await SharedPreferences.getInstance();
      String? token = await prefs.getString('jwt');
      return token.toString();
  }

  Future<String> getUsername() async {
      String token = await getJwt();
      Map<String, dynamic> decodedToken = JwtDecoder.decode(token);
      String username = decodedToken['sub'];
      return username;
  }

  Future<List<Buku>> fetchBuku() async {
      String token = await getJwt();
      var url = Uri.parse('http://localhost:8080/api/buku/view-all');
      var response = await http.get(
        url,
        headers: {
            "Authorization": 'Bearer $token',
            "Content-Type": "application/json",
        },
      );

      var data = jsonDecode(utf8.decode(response.bodyBytes));

      List<Buku> listBuku = [];
      for (var d in data) {
        if (d != null) {
            listBuku.add(Buku.fromJson(d));
        }
      }

      return listBuku;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
            elevation: 0,
            automaticallyImplyLeading: false,
            backgroundColor: const Color(0XFF5d675b),
            actions: [

            Padding(
            padding: const EdgeInsets.only(right: 10),
            child: IconButton (
            onPressed: () {
            popUpExit(
                context, "Log out of your account?");
            },
            icon: const Icon(
            Icons.account_circle_outlined,
            size: 40,
            color: Colors.white,
          ),
        )),
    ],
    ),
      body:  FutureBuilder<List<Buku>?>(
        future: fetchBuku(), // a previously-obtained Future<String> or null
        builder: (BuildContext context, AsyncSnapshot<List<Buku>?> snapshot) {
          if (snapshot.data == null) {
            if (snapshot.connectionState == ConnectionState.done) {
              return const Padding(
                padding: EdgeInsets.all(12),
                child: Center(
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      Text(
                        "Buku tidak ditemukan",
                        style: TextStyle(
                            fontSize: 16),
                      ),
                      SizedBox(height: 8),
                    ],
                  ),
                ),
              );
            }
            return const Center(
                child: CircularProgressIndicator()
            );
          }

          if (!snapshot.hasData) {
            return const Column(
              children: [
                Text(
                  "Buku tidak ditemukan",
                  style: TextStyle(
                      fontSize: 16),
                ),
                SizedBox(height: 8),
              ],
            );
          } else {
            final data = snapshot.data as List<Buku>;

            return Padding(
              padding: const EdgeInsets.all(20),
              child: Column(children: [
                  Center(
                    child: FutureBuilder<String> (
                      future: getUsername(),
                      builder: (BuildContext context, AsyncSnapshot<String> snapshot) {
                          return Text(
                            'Halo, ${snapshot.data}',
                            style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
                          );
                      }
                    )
                  ),
                  Expanded(child: 
                  ListView.builder(
                    shrinkWrap: true,
                    itemCount: snapshot.data!.length,
                    itemBuilder: (_, index)=> Container(
                      margin: const EdgeInsets.symmetric(horizontal: 16, vertical: 12),
                      padding: const EdgeInsets.all(20.0),
                      decoration: BoxDecoration(
                          color:Colors.white,
                          borderRadius: BorderRadius.circular(15.0),
                          boxShadow: const [
                            BoxShadow(
                                color: Colors.black,
                                blurRadius: 2.0
                            )
                          ]
                      ),
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.start,
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text.rich(
                            style: TextStyle(fontSize: 15),
                            TextSpan(
                              children: [
                                TextSpan(text: "Judul: ", style: TextStyle(fontWeight: FontWeight.bold)),
                                TextSpan(text: data[index].judul),
                              ],
                            ),
                          ),
                          Text.rich(
                            style: TextStyle(fontSize: 15),
                            TextSpan(
                              children: [
                                TextSpan(text: "Tahun Terbit: ", style: TextStyle(fontWeight: FontWeight.bold)),
                                TextSpan(text: data[index].tahunTerbit),
                              ],
                            ),
                          ),
                          Text.rich(
                            style: TextStyle(fontSize: 15),
                            TextSpan(
                              children: [
                                TextSpan(text: "Harga: ", style: TextStyle(fontWeight: FontWeight.bold)),
                                TextSpan(text: data[index].harga.toString()),
                              ],
                            ),
                          ),
                          Text.rich(
                            style: TextStyle(fontSize: 15),
                            TextSpan(
                              children: [
                                TextSpan(text: "Penerbit: ", style: TextStyle(fontWeight: FontWeight.bold)),
                                TextSpan(text: data[index].penerbit.namaPenerbit),
                              ],
                            ),
                          ),
                        ],
                      ),
                    )
                  ),
                )
                ],
              )
            );
          }
        },
      ),
    );
  }
}
