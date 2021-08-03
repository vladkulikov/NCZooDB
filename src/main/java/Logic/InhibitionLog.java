package Logic;

import Model.Species;

import java.util.Date;

public class InhibitionLog {
    private Date checkInDate;
    private Date checkOutDate;
    private Species animalSpecies;
    private String animalName;
    private String result;


    @Override
    public String toString() {
        return "Logic.InhibitionLog{" +
                "checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", animalSpecies=" + animalSpecies +
                ", animalName=" + animalName  +
                ", result=" + result + "}";
    }

    static class Builder{
        private final InhibitionLog inhibitionLog;
        public Builder(){
            inhibitionLog = new InhibitionLog();
        }

        public Builder withInDate(Date checkInDate){
            inhibitionLog.checkInDate = checkInDate;
            return this;
        }


        public Builder withOutDate(Date checkOutDate){
            inhibitionLog.checkOutDate = checkOutDate;
            return this;
        }


        public Builder withSpecies(Species species){
            inhibitionLog.animalSpecies = species;
            return this;
        }

        public Builder withName(String name){
            inhibitionLog.animalName = name;
            return this;
        }

        public Builder withResult(String result){
            inhibitionLog.result = result;
            return this;
        }

        public InhibitionLog build(){
            return inhibitionLog;
        }
    }

}