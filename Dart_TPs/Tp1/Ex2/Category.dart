import "Ordinateur.dart";

class Category {
  String name;
  String description;

  List<Ordinateur> ordinateurs = [];

  Category(this.name, this.description, this.ordinateurs);
  Category.withoutOrd(this.name, this.description);

  void ajouterOrdinateur(Ordinateur ordinateur) {
    if (ordinateurs.contains(ordinateur))
      print("${ordinateur.name} already exist !!");
    else
      ordinateurs.add(ordinateur);
  }

  void supprimerOrdinateur(Ordinateur ordinateur) {
    if (ordinateurs.remove(ordinateur)) {
      print("${ordinateur.name} was removed successfully !!");
    } else {
      print("${ordinateur.name} isn't in $name category !!");
    }
  }

  List<Ordinateur> rechercherParPrix(double price) {
    List<Ordinateur> samePrice = [];
    ordinateurs.forEach((e) {
      if (e.price == price) {
        samePrice.add(e);
      }
    });

    return samePrice;
  }
}
