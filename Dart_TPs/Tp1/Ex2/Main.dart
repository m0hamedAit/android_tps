import "Category.dart";
import 'Client.dart';
import 'Commande.dart';
import 'LigneCommande.dart';
import 'Ordinateur.dart';

void main() {
  //categories
  Category cat1 = new Category.withoutOrd("Work", "Laptops for work");
  Category cat2 = new Category.withoutOrd("Gaming", "Laptops for Gaming");
  //Computers
  List<Ordinateur> ordinateurs = [
    new Ordinateur(
        "ASUS L410",
        "Asus",
        438,
        "Ultra Thin Laptop, 14” FHD Display, Intel Celeron N4020 Processor, 4GB RAM, 64GB Storage",
        11,
        cat1),
    new Ordinateur(
        "Aspire 5 A515-46-R3UB",
        "Acer",
        696,
        "15.6' Full HD IPS Display | AMD Ryzen 3 3350U Quad-Core Mobile Processor | 4GB DDR4 | 128GB NVMe SSD",
        20,
        cat1),
    new Ordinateur(
        "ASUS TUF Dash 15",
        "Asus",
        1500,
        "15.6” 144Hz FHD Display, Intel Core i7-12650H, GeForce RTX 3060, 16GB DDR5, 512GB SSD",
        5,
        cat2)
  ];
  //add computers to categories
  cat1.ajouterOrdinateur(ordinateurs[0]);
  cat1.ajouterOrdinateur(ordinateurs[1]);
  cat2.ajouterOrdinateur(ordinateurs[2]);
  // client

  Client client = new Client.withoutCommande("A", "Mohamed", "Address",
      "mohamed@gmail.com", "Casablanca", "0658974213");

  Commande commande =
      new Commande.withoutCL("EZ132154", client, DateTime.now(), "delivered");

  client.addCommande(commande);

  List<LigneCommande> commandeLignes = [
    new LigneCommande(2, commande, ordinateurs[2]),
    new LigneCommande(1, commande, ordinateurs[1])
  ];

  commande.addCommandeLignes(commandeLignes[0]);
  commande.addCommandeLignes(commandeLignes[1]);

  print(commande);
}
