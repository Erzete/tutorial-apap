// To parse this JSON data, do
//
//     final buku = bukuFromJson(jsonString);

import 'dart:convert';

List<Buku> bukuFromJson(String str) => List<Buku>.from(json.decode(str).map((x) => Buku.fromJson(x)));

String bukuToJson(List<Buku> data) => json.encode(List<dynamic>.from(data.map((x) => x.toJson())));

class Buku {
    String id;
    String judul;
    String tahunTerbit;
    double harga;
    Penerbit penerbit;

    Buku({
        required this.id,
        required this.judul,
        required this.tahunTerbit,
        required this.harga,
        required this.penerbit,
    });

    factory Buku.fromJson(Map<String, dynamic> json) => Buku(
        id: json["id"],
        judul: json["judul"],
        tahunTerbit: json["tahunTerbit"],
        harga: json["harga"]?.toDouble(),
        penerbit: Penerbit.fromJson(json["penerbit"]),
    );

    Map<String, dynamic> toJson() => {
        "id": id,
        "judul": judul,
        "tahunTerbit": tahunTerbit,
        "harga": harga,
        "penerbit": penerbit.toJson(),
    };
}

class Penerbit {
    int idPenerbit;
    String namaPenerbit;
    String alamat;
    String email;
    bool deleted;

    Penerbit({
        required this.idPenerbit,
        required this.namaPenerbit,
        required this.alamat,
        required this.email,
        required this.deleted,
    });

    factory Penerbit.fromJson(Map<String, dynamic> json) => Penerbit(
        idPenerbit: json["idPenerbit"],
        namaPenerbit: json["namaPenerbit"],
        alamat: json["alamat"],
        email: json["email"],
        deleted: json["deleted"],
    );

    Map<String, dynamic> toJson() => {
        "idPenerbit": idPenerbit,
        "namaPenerbit": namaPenerbit,
        "alamat": alamat,
        "email": email,
        "deleted": deleted,
    };
}
