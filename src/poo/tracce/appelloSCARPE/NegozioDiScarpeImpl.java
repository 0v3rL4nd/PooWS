package poo.tracce.appelloSCARPE;

//import java.util.*;
//
//public class NegozioDiScarpeImpl extends AbstractNegozioScarpe {
//
//    private class Coppia {
//        private final String modelloScarpa;
//        private final int misura;
//
//        public Coppia(String modelloScarpa, int misura){
//            this.modelloScarpa = modelloScarpa;
//            this.misura = misura;
//        }
//
//        public Coppia( Coppia c ){
//            this.modelloScarpa = c.modelloScarpa;
//            this.misura = c.misura;
//        }
//    }
//
//    private static class Set<T>{
//        private final List<T> list = new ArrayList<>();
//
//        public boolean add(T t){
//            return list.add( t );
//        }
//
//
//    }
//
//
//    List<Coppia> cp =  new ArrayList<>();
//
//    public NegozioDiScarpeImpl(int misura, String modelloScarpa) {
//        super(misura, modelloScarpa);
//    }
//
//    public NegozioDiScarpeImpl(AbstractNegozioScarpe n) {
//        super(n);
//    }
//
//
//    @Override
//    public void aggiungi(String modelloScarpa, int misura) {
//        cp.add(new Coppia(modelloScarpa, misura));
//    }
//
//    @Override
//    public boolean vendi(String modelloScarpa, int misura) throws IllegalArgumentException {
//        try{
//            for( Coppia c: cp){
//                if( c.modelloScarpa.equals(modelloScarpa) && c.misura == misura){
//                    return cp.remove(c);
//
//                }
//            }
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Scarpa non disponibile");
//        }
//        return false;
//    }
//
//    @Override
//    public Set<Integer> misureDisponibili(String modelloScarpa) {
//        Set<Integer> result = new Set<>();
//        for( Coppia c: cp){
//            if( c.modelloScarpa.equals(modelloScarpa)){
//                result.add(c.misura);
//            }
//        }
//        return result;
//    }
//
//    @Override
//    public Set<String> scarpeDisponibili() {
//        Set<String> res = new Set<>();
//        for( Coppia c: cp){
//
//        }
//    }
//
//    @Override
//    public boolean eScarpaDisponibile(String modelloScarpa, int misura) {
//        for( Coppia c: cp){
//            if( c.modelloScarpa.equals(modelloScarpa) && c.misura == misura){
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public Iterator<String> scarpeDisponibile(int misura) {
//        return null;
//    }
//}
