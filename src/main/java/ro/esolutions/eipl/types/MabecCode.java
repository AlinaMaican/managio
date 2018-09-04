package ro.esolutions.eipl.types;

public enum MabecCode {
    MABEC_01,
    MABEC_02,
    MABEC_03,
    MABEC_04,
    MABEC_05,
    MABEC_06,
    MABEC_07;

   public static boolean contains(String code){
        for(MabecCode mabecCode: MabecCode.values()) {
            if (mabecCode.name().equals(code)) {
                return true;
            }
        }
           return false;
   }
}
