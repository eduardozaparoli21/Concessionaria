import javax.swing.*;
import java.awt.*;

public class FinanciamentoFrame extends JFrame {

    public FinanciamentoFrame() {
        setSize(600, 600);
        setResizable(false);
        setTitle("Financiamento de Veículos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        criarTela();

        setLocationRelativeTo(null);
    }

    private void criarTela() {

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(0, 2, 5, 5));


        painel.add(new JLabel("Financiamento de Carros"));
        painel.add(new JLabel(""));

        painel.add(new JLabel("Marca:"));

        JComboBox<String> marca = new JComboBox<>();

        marca.addItem("FIAT");
        marca.addItem("Toyota");
        marca.addItem("Volkswagen");
        marca.addItem("Chevrolet");

        painel.add(marca);


        painel.add(new JLabel("Modelo:"));

        JTextField modelo = new JTextField();
        painel.add(modelo);


        painel.add(new JLabel("Ano:"));

        JComboBox<String> ano = new JComboBox<>();

        for (int i = 2026; i >= 2000; i--) {
            ano.addItem(String.valueOf(i));
        }

        painel.add(ano);


        painel.add(new JLabel("Valor:"));

        JTextField valor = new JTextField();
        painel.add(valor);

        painel.add(new JLabel("Tipo:"));

        JPanel tipo = new JPanel();

        JRadioButton novo = new JRadioButton("Novo");
        JRadioButton usado = new JRadioButton("Usado");

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(novo);
        grupo.add(usado);

        novo.setSelected(true);

        tipo.add(novo);
        tipo.add(usado);

        painel.add(tipo);



        JLabel labelQuilometragem =
                new JLabel("Quilometragem:");

        JTextField quilometragem =
                new JTextField();

        painel.add(labelQuilometragem);
        painel.add(quilometragem);

        JLabel labelProprietarios =
                new JLabel("Proprietários:");

        JTextField proprietarios =
                new JTextField();

        painel.add(labelProprietarios);
        painel.add(proprietarios);


        labelQuilometragem.setVisible(false);
        quilometragem.setVisible(false);

        labelProprietarios.setVisible(false);
        proprietarios.setVisible(false);

        usado.addActionListener(e -> {

            labelQuilometragem.setVisible(true);
            quilometragem.setVisible(true);

            labelProprietarios.setVisible(true);
            proprietarios.setVisible(true);

            painel.revalidate();
            painel.repaint();
        });


        novo.addActionListener(e -> {

            labelQuilometragem.setVisible(false);
            quilometragem.setVisible(false);

            labelProprietarios.setVisible(false);
            proprietarios.setVisible(false);

            painel.revalidate();
            painel.repaint();
        });


        painel.add(new JLabel("Possui entrada?"));

        JCheckBox entradaCheck =
                new JCheckBox("Sim");

        painel.add(entradaCheck);

        JLabel labelEntrada =
                new JLabel("Entrada:");

        JTextField entrada =
                new JTextField();

        painel.add(labelEntrada);
        painel.add(entrada);


        labelEntrada.setVisible(false);
        entrada.setVisible(false);

        entradaCheck.addActionListener(e -> {

            boolean selecionado =
                    entradaCheck.isSelected();

            labelEntrada.setVisible(selecionado);
            entrada.setVisible(selecionado);

            painel.revalidate();
            painel.repaint();
        });


        painel.add(new JLabel("Parcelas:"));

        JComboBox<String> parcelas =
                new JComboBox<>();

        parcelas.addItem("12");
        parcelas.addItem("24");
        parcelas.addItem("36");
        parcelas.addItem("48");
        parcelas.addItem("60");

        painel.add(parcelas);

        JLabel valorFinanciadoLabel =
                new JLabel("R$ 0,00");

        JLabel valorParcelaLabel =
                new JLabel("R$ 0,00");

        JLabel totalPagarLabel =
                new JLabel("R$ 0,00");

        painel.add(new JLabel("Valor financiado:"));
        painel.add(valorFinanciadoLabel);

        painel.add(new JLabel("Valor da parcela:"));
        painel.add(valorParcelaLabel);

        painel.add(new JLabel("Total a pagar:"));
        painel.add(totalPagarLabel);

        JButton calcular =
                new JButton("CALCULAR");

        JButton limpar =
                new JButton("LIMPAR");

        painel.add(calcular);
        painel.add(limpar);


        calcular.addActionListener(e -> {

            try {


                double valorVeiculo =
                        Double.parseDouble(
                                valor.getText()
                                        .replace(",", ".")
                        );


                double valorEntrada = 0;

                if (entradaCheck.isSelected()) {

                    valorEntrada =
                            Double.parseDouble(
                                    entrada.getText()
                                            .replace(",", ".")
                            );
                }


                double valorFinanciado =
                        valorVeiculo - valorEntrada;


                int numeroParcelas =
                        Integer.parseInt(
                                parcelas
                                        .getSelectedItem()
                                        .toString()
                        );


                double valorTotal =
                        valorFinanciado * 1.32;


                double calculoParcela =
                        valorTotal / numeroParcelas;


                double total =
                        calculoParcela * numeroParcelas;


                valorFinanciadoLabel.setText(
                        String.format(
                                "R$ %.2f",
                                valorFinanciado
                        )
                );

                valorParcelaLabel.setText(
                        String.format(
                                "R$ %.2f",
                                calculoParcela
                        )
                );

                totalPagarLabel.setText(
                        String.format(
                                "R$ %.2f",
                                total
                        )
                );

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Digite valores válidos.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });


        limpar.addActionListener(e -> {

            modelo.setText("");
            valor.setText("");
            entrada.setText("");
            quilometragem.setText("");
            proprietarios.setText("");

            marca.setSelectedIndex(0);
            ano.setSelectedIndex(0);
            parcelas.setSelectedIndex(0);

            novo.setSelected(true);
            entradaCheck.setSelected(false);

            labelQuilometragem.setVisible(false);
            quilometragem.setVisible(false);

            labelProprietarios.setVisible(false);
            proprietarios.setVisible(false);

            labelEntrada.setVisible(false);
            entrada.setVisible(false);

            valorFinanciadoLabel.setText("R$ 0,00");
            valorParcelaLabel.setText("R$ 0,00");
            totalPagarLabel.setText("R$ 0,00");

            painel.revalidate();
            painel.repaint();
        });


        add(painel);
    }



    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            FinanciamentoFrame tela =
                    new FinanciamentoFrame();

            tela.setVisible(true);
        });
    }
}