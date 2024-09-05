package com.example.calculorestaurante_layout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edt_consumo, edt_couvert, edt_dividir;
    private TextView txtv_taxa, txtv_total, txtv_repartido;
    private Button btn_calcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_consumo = findViewById(R.id.edt_consumo);
        edt_couvert = findViewById(R.id.edt_couvert);
        edt_dividir = findViewById(R.id.edt_dividir);
        txtv_taxa = findViewById(R.id.txtv_taxa);
        txtv_total = findViewById(R.id.txtv_total);
        txtv_repartido = findViewById(R.id.txtv_repartido);
        btn_calcular = findViewById(R.id.btn_calcular);

        btn_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularConta();
            }
        });
    }

    private void calcularConta() {
        String consumoStr = edt_consumo.getText().toString();
        String couvertStr = edt_couvert.getText().toString();
        String dividirStr = edt_dividir.getText().toString();

        // Verificar se os campos estão vazios
        if (consumoStr.isEmpty() || couvertStr.isEmpty() || dividirStr.isEmpty()) {
            txtv_taxa.setText("Erro: Preencha todos os campos");
            txtv_total.setText("");
            txtv_repartido.setText("");
            return;
        }

        try {
            double consumoTotal = Double.parseDouble(consumoStr);
            double couvertArtistico = Double.parseDouble(couvertStr);
            int numPessoas = Integer.parseInt(dividirStr);

            if (numPessoas <= 0) {
                txtv_taxa.setText("Erro: Número de pessoas deve ser maior que zero");
                txtv_total.setText("");
                txtv_repartido.setText("");
                return;
            }

            double taxaServico = 0.1 * consumoTotal;
            double contaTotal = consumoTotal + couvertArtistico + taxaServico;
            double valorPorPessoa = contaTotal / numPessoas;

            txtv_taxa.setText(String.format("Taxa de Serviço (10%%):\nR$ %.2f", taxaServico));
            txtv_total.setText(String.format("Conta Total: R$ %.2f", contaTotal));
            txtv_repartido.setText(String.format("Valor por Pessoa: R$ %.2f", valorPorPessoa));

        } catch (NumberFormatException e) {
            e.printStackTrace();
            txtv_taxa.setText("Erro: Entrada inválida");
            txtv_total.setText("");
            txtv_repartido.setText("");
        }
    }
}
