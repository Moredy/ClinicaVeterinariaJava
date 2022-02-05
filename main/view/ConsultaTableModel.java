package view;

import model.Consulta;
import model.ConsultaDAO;
import model.Tratamento;
import model.TratamentoDAO;

import java.util.List;

public class ConsultaTableModel extends GenericTableModel{

  public ConsultaTableModel(List vDados) {
      super(vDados, new String[]{"Data", "Horário", "Comentário", "Id Animal", "Id vet", "Id tratamento", "Terminado"});
  }
      @Override
      public Class<?> getColumnClass(int columnIndex) {
          switch (columnIndex) {
              case 0:
                  return String.class;
              case 1:
                  return Integer.class;
              case 2:
                  return String.class;
              case 3:
                  return Integer.class;
              case 4:
                  return Integer.class;
              case 5:
                  return Integer.class;
              case 6:
                  return Integer.class;
              default:
                  throw new IndexOutOfBoundsException("columnIndex out of bounds");
          }

      }


      @Override
      public Object getValueAt (int rowIndex, int columnIndex) {
          Consulta consult = (Consulta) vDados.get (rowIndex);

          switch (columnIndex) {
              case 0:
                  return consult.getData();
              case 1:
                  return consult.getHora();
              case 2:
                  return consult.getComentario();
              case 3:
                  return consult.getIdAnimal();
              case 4:
                  return consult.getIdVet();
              case 5:
                  return consult.getIdTratamento();
              case 6:
                  return consult.isTerminou();
              default:
                  throw new IndexOutOfBoundsException("coloumIndex out of bounds");
          }

      }

      @Override
      public void setValueAt (Object aValue, int rowIndex, int columnIndex) {
      Consulta consult = (Consulta) vDados.get(rowIndex);

      switch (columnIndex) {
          case 0:
              consult.setData((String) aValue);
              break;
          case 1:
              consult.setHora((Integer) aValue);
              break;
          case 2:
              consult.setComentarios((String)aValue);
              break;
          case 3:
              consult.setIdAnimal((Integer) aValue);
              break;
          case 4:
              consult.setIdVet((Integer) aValue);
              break;
          case 5:
              consult.setIdTratamento((Integer) aValue);
              break;
          case 6:
              consult.setTerminou((Integer) aValue);
              break;
          default:
              throw new IndexOutOfBoundsException("coloumIndex out of bounds");
      }

      ConsultaDAO.getInstance().update(consult);

  }

  @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
      return true;
  }

}
