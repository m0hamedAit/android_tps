import "Commande.dart";
import "Ordinateur.dart";

class LigneCommande {
  int quantity;
  Commande commande;
  Ordinateur ordinateur;

  LigneCommande(this.quantity, this.commande, this.ordinateur);

  @override
  String toString() {
    return '''
      ordinateur : $ordinateur
      quantity : $quantity
      total : ${ordinateur.price * quantity} \$
    ''';
  }
}
