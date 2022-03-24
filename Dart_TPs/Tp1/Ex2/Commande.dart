import "Client.dart";
import 'LigneCommande.dart';

class Commande {
  String reference;
  Client client;
  var date;
  String commandeStatus;
  List<LigneCommande> commandeLignes = [];

  Commande(this.reference, this.client, this.date, this.commandeStatus,
      this.commandeLignes);
  Commande.withoutCL(
      this.reference, this.client, this.date, this.commandeStatus);

  void addCommandeLignes(LigneCommande ligneCommande) {
    commandeLignes.add(ligneCommande);
  }

  @override
  String toString() {
    String str = "";
    double somme = 0;
    commandeLignes.forEach((e) {
      str += e.toString();
      somme += e.quantity * e.ordinateur.price;
    });

    return '''
  Commande $reference :\n
  \tDate : $date
  \tStatus : $commandeStatus
  \tClient : 
    $client
  ''' +
        str +
        '''
  }

  Total : $somme\$

''';
  }
}
