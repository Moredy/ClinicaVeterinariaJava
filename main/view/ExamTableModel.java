package view;

import model.*;

import java.util.List;

public class ExamTableModel extends GenericTableModel{

  public ExamTableModel(List vDados) {
      super(vDados, new String[]{"Nome", "id Consulta"});
  }
      @Override
      public Class<?> getColumnClass(int columnIndex) {
          switch (columnIndex) {
              case 0:
                  return String.class;
              case 1:
                  return Integer.class;
              default:
                  throw new IndexOutOfBoundsException("columnIndex out of bounds");
          }

      }


      @Override
      public Object getValueAt (int rowIndex, int columnIndex) {
          Exame exam = (Exame) vDados.get (rowIndex);

          switch (columnIndex) {
              case 0:
                  return exam.getDescricao();
              case 1:
                  return exam.getIdConsulta();
              default:
                  throw new IndexOutOfBoundsException("coloumIndex out of bounds");
          }

      }

      @Override
      public void setValueAt (Object aValue, int rowIndex, int columnIndex) {
      Exame exam = (Exame) vDados.get(rowIndex);

      switch (columnIndex) {
          case 0:
              exam.setDescricao((String)aValue);
              break;
          case 1:
              exam.setIdConsulta((Integer) aValue);
              break;
          default:
              throw new IndexOutOfBoundsException("coloumIndex out of bounds");
      }

      ExameDAO.getInstance().update(exam);

  }

  @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
      return true;
  }

}
