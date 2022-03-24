class Response{
  String title;
  bool correct;
  
  Response(this.title, this.correct);
  
  @override
  String toString() {
    if(this.correct == true){
     return "\t\t${title} : \x1B[32m$correct\x1B[0m \n";
    }
    else{
      return "\t\t${title} : \x1B[31m$correct\x1B[0m \n";
    }
  }
  
}