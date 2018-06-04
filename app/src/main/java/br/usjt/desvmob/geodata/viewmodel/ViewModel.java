package br.usjt.desvmob.geodata.viewmodel;

import java.util.ArrayList;

import br.usjt.desvmob.geodata.model.Pais;

/**
 * Tatiane Ayres 816118367
 */

public interface ViewModel {
    ArrayList<String> listarNomes(Pais[] paises);
    Pais[] listarPaises(String continente);
    ArrayList<Pais> todosPaises() ;
}
