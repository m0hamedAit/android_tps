import "Commande.dart";

class Client {
  String nom;
  String prenom;
  String adresse;
  String email;
  String ville;
  String telephone;
  List<Commande> commandes = [];

  Client(this.nom, this.prenom, this.adresse, this.email, this.ville,
      this.telephone, this.commandes);
  Client.withoutCommande(this.nom, this.prenom, this.adresse, this.email,
      this.ville, this.telephone);

  void addCommande(Commande commande) {
    if (commandes.contains(commande)) {
      print("Commande ${commande.reference} already exist !!");
    } else {
      commandes.add(commande);
    }
  }

  void deleteCommande(Commande commande) {
    if (commandes.remove(commande)) {
      print("Commande ${commande.reference} was removed successfully !!");
    } else {
      print("Commande ${commande.reference} isn't in DB !!");
    }
  }

  @override
  String toString() {
    return '''{
      name : $nom $prenom
      adresse : $adresse, $ville
      email : $email
      tel : $telephone
    }''';
  }
}
