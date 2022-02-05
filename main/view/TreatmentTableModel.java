package view;

import model.Tratamento;
import model.TratamentoDAO;
import model.Veterinario;
import model.VeterinarioDAO;

import java.util.List;

public class TreatmentTableModel extends GenericTableModel{

  public TreatmentTableModel(List vDados) {
      super(vDados, new String[]{"id Tratamento" , "id Animal", "nome", "dataInit", "dataFim", "terminado"});
  }
      @Override
      public Class<?> getColumnClass(int columnIndex) {
          switch (columnIndex) {
              case 0:
                  return Integer.class;
              case 1:
                  return Integer.class;
              case 2:
                  return String.class;
              case 3:
                  return String.class;
              case 4:
                  return String.class;
              case 5:
                  return Integer.class;
              default:
                  throw new IndexOutOfBoundsException("columnIndex out of bounds");
          }

      }


      @Override
      public Object getValueAt (int rowIndex, int columnIndex) {
          Tratamento trat = (Tratamento) vDados.get (rowIndex);

          switch (columnIndex) {
              case 0:
                  return trat.getId();
              case 1:
                  return trat.getIdAnimal();
              case 2:
                  return trat.getNome();
              case 3:
                  return trat.getDtInicio();
              case 4:
                  return trat.getDtFim();
              case 5:
                  return trat.getTerminou();
              default:
                  throw new IndexOutOfBoundsException("coloumIndex out of bounds");
          }

      }

      @Override
      public void setValueAt (Object aValue, int rowIndex, int columnIndex) {
      Tratamento trat = (Tratamento) vDados.get(rowIndex);

      switch (columnIndex) {
          case 0:
              break;
          case 1:
              //trat.setIdAnimal((Integer) aValue);
              break;
          case 2:
              trat.setNome((String)aValue);
              break;
          case 3:
              trat.setDtInicio((String)aValue);
              break;
          case 4:
              trat.setDtFim((String)aValue);
              break;
          case 5:
              trat.setTerminou((Integer) aValue);
              break;
          default:
              throw new IndexOutOfBoundsException("coloumIndex out of bounds");
      }

      TratamentoDAO.getInstance().update(trat);

  }

  @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
      return true;
  }

}
