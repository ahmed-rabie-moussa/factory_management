
package factorysystem;

import javafx.beans.property.SimpleStringProperty;

public class Report {
    private  SimpleStringProperty from;
     private  SimpleStringProperty to;
      private  SimpleStringProperty description;

    public Report(String from , String to , String description) {
        this.from = new SimpleStringProperty(from);
        this.to = new SimpleStringProperty(to);
        this.description = new SimpleStringProperty(description);
    }
      public void setFrom (String from){
      this.from.set(from);
      }
      
      public String getFrom (){
          return this.from.get();
}
        public void setTo (String to){
      this.to.set(to);
      }
        
      public String getTo (){
          return this.to.get();
}
      
        public void setDescription (String description){
      this.description.set(description);
      }
        
      public String getDescription (){
          return this.description.get();
}
}