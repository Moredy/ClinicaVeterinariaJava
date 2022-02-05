package view;

import model.Animal;
import model.AnimalDAO;
import model.Especie;
import model.EspecieDAO;

import java.util.List;

public class AnimalTableModel extends GenericTableModel{

  public AnimalTableModel(List vDados) {
      super(vDados, new String[]{"Nome", "Ano Nascimento", "Sexo", "Espécie"});
  }
      @Override
      public Class<?> getColumnClass(int columnIndex) {
          switch (columnIndex) {
              case 0:
                  return String.class;
              case 1:
                  return String.class;
              case 2:
                  return String.class;
              case 3:
                  return String.class;
              default:
                  throw new IndexOutOfBoundsException("columnIndex out of bounds");
          }

      }


      @Override
      public Object getValueAt (int rowIndex, int columnIndex) {
          Animal animal = (Animal) vDados.get (rowIndex);

          switch (columnIndex) {
              case 0:
                  return animal.getNome();
              case 1:
                  return animal.getAnoNasc();
              case 2:
                  return animal.getSexo();
              case 3:
                  Especie especie = EspecieDAO.getInstance().retrieveById(animal.getIdEspecie());
                  if(especie != null) {
                      return especie.getNome();
                  }
                  return "";
              default:
                  throw new IndexOutOfBoundsException("coloumIndex out of bounds");
          }

      }

      @Override
      public void setValueAt (Object aValue, int rowIndex, int columnIndex) {
      Animal animal = (Animal) vDados.get(rowIndex);

      switch (columnIndex) {
          case 0:
              animal.setNome((String)aValue);
              break;
          case 1:
              animal.setAnoNasc((String) aValue);
              break;
          case 2:
              //System.out.println(aValue);
              animal.setSexo((String) aValue);
              break;
          case 3:
              try {
                  Especie especie = (Especie) EspecieDAO.getInstance().retrieveBySimilarName((String) aValue);
                  if (especie == null) {
                      especie = EspecieDAO.getInstance().create((String) aValue);
                  }

                  animal.setIdEspecie(especie.getId());
              } catch (Exception e) {
                  animal.setIdEspecie(EspecieDAO.getInstance().create((String) aValue).getId());
              }
              break;
          default:
              throw new IndexOutOfBoundsException("coloumIndex out of bounds");
      }

      AnimalDAO.getInstance().update(animal);

  }

  @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
      return true;
  }

}
