Codigo navegador de arquivos 

  package com.mycompany.navegadordearquivos;
  
  import java.io.File;
  import javax.swing.JOptionPane;
  import java.util.ArrayList;
  import java.util.List;
  
  public class NavegadorDeArquivos {
  
      private File diretorioAtual;
  
      public NavegadorDeArquivos() {
          
          diretorioAtual = new File(System.getProperty("user.home"));
          if (!diretorioAtual.exists() || !diretorioAtual.isDirectory()) {
           
              File[] roots = File.listRoots();
              if (roots.length > 0) {
                  diretorioAtual = roots[0];
              } else {
                  JOptionPane.showMessageDialog(null, "Não foi possível encontrar um diretório inicial acessível.", "Erro de Inicialização", JOptionPane.ERROR_MESSAGE);
                  System.exit(1); 
              }
          }
          JOptionPane.showMessageDialog(null, "Navegador de Arquivos Básico - Início\nDiretório atual: " + diretorioAtual.getAbsolutePath(), "Bem-vindo", JOptionPane.INFORMATION_MESSAGE);
      }
  
      public void iniciarNavegacao() {
          while (true) {
              String[] opcoes = {"Listar Conteúdo", "Entrar em Subdiretório", "Voltar ao Diretório Pai", "Sair"};
              int escolha = JOptionPane.showOptionDialog(
                      null,
                      "Diretório Atual: " + diretorioAtual.getAbsolutePath() + "\nEscolha uma opção:",
                      "Menu Principal",
                      JOptionPane.DEFAULT_OPTION,
                      JOptionPane.PLAIN_MESSAGE,
                      null,
                      opcoes,
                      opcoes[0]
              );
  
              switch (escolha) {
                  case 0: 
                      listarConteudoDiretorio();
                      break;
                  case 1: 
                      entrarEmSubdiretorio();
                      break;
                  case 2: 
                      voltarAoDiretorioPai();
                      break;
                  case 3:
                      JOptionPane.showMessageDialog(null, "Saindo do Navegador de Arquivos. Até logo!", "Sair", JOptionPane.INFORMATION_MESSAGE);
                      return; 
                  case JOptionPane.CLOSED_OPTION: 
                      JOptionPane.showMessageDialog(null, "Saindo do Navegador de Arquivos. Até logo!", "Sair", JOptionPane.INFORMATION_MESSAGE);
                      return;
                  default:
                      JOptionPane.showMessageDialog(null, "Opção inválida. Por favor, tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
                      break;
              }
          }
      }
  
      private void listarConteudoDiretorio() {
          File[] arquivos = diretorioAtual.listFiles();
          if (arquivos == null || arquivos.length == 0) {
              JOptionPane.showMessageDialog(null, "O diretório '" + diretorioAtual.getName() + "' está vazio ou inacessível.", "Conteúdo do Diretório", JOptionPane.INFORMATION_MESSAGE);
              return;
          }
  
          StringBuilder sb = new StringBuilder("Conteúdo de: " + diretorioAtual.getAbsolutePath() + "\n\n");
          List<File> diretorios = new ArrayList<>();
          List<File> files = new ArrayList<>();
  
          for (File arquivo : arquivos) {
              if (arquivo.isDirectory()) {
                  diretorios.add(arquivo);
              } else {
                  files.add(arquivo);
              }
          }
  
         
          diretorios.sort((f1, f2) -> f1.getName().compareToIgnoreCase(f2.getName()));
          files.sort((f1, f2) -> f1.getName().compareToIgnoreCase(f2.getName()));
  
          for (File dir : diretorios) {
              sb.append("[DIR] ").append(dir.getName()).append("\n");
          }
          for (File file : files) {
              sb.append("[ARQ] ").append(file.getName()).append("\n");
          }
  
          JOptionPane.showMessageDialog(null, sb.toString(), "Conteúdo do Diretório", JOptionPane.PLAIN_MESSAGE);
      }
  
      private void entrarEmSubdiretorio() {
          String nomeSubdiretorio = JOptionPane.showInputDialog(null, "Digite o nome do subdiretório para entrar:", "Entrar em Subdiretório", JOptionPane.QUESTION_MESSAGE);
  
          if (nomeSubdiretorio == null || nomeSubdiretorio.trim().isEmpty()) {
              return; 
          }
  
          File novoDiretorio = new File(diretorioAtual, nomeSubdiretorio);
  
          if (!novoDiretorio.exists()) {
              JOptionPane.showMessageDialog(null, "O diretório '" + nomeSubdiretorio + "' não existe.", "Erro", JOptionPane.ERROR_MESSAGE);
          } else if (!novoDiretorio.isDirectory()) {
              JOptionPane.showMessageDialog(null, "'" + nomeSubdiretorio + "' não é um diretório. Não é possível entrar.", "Erro", JOptionPane.ERROR_MESSAGE);
          } else if (!novoDiretorio.canRead()) {
              JOptionPane.showMessageDialog(null, "Sem permissão para acessar o diretório '" + nomeSubdiretorio + "'.", "Erro de Permissão", JOptionPane.ERROR_MESSAGE);
          } else {
              diretorioAtual = novoDiretorio;
              JOptionPane.showMessageDialog(null, "Você entrou em: " + diretorioAtual.getAbsolutePath(), "Navegação", JOptionPane.INFORMATION_MESSAGE);
          }
      }
  
      private void voltarAoDiretorioPai() {
          File diretorioPai = diretorioAtual.getParentFile();
  
          if (diretorioPai == null) {
              JOptionPane.showMessageDialog(null, "Você já está no diretório raiz. Não é possível voltar mais.", "Navegação", JOptionPane.WARNING_MESSAGE);
          } else if (!diretorioPai.canRead()) {
              JOptionPane.showMessageDialog(null, "Sem permissão para acessar o diretório pai.", "Erro de Permissão", JOptionPane.ERROR_MESSAGE);
          }
          else {
              diretorioAtual = diretorioPai;
              JOptionPane.showMessageDialog(null, "Você voltou para: " + diretorioAtual.getAbsolutePath(), "Navegação", JOptionPane.INFORMATION_MESSAGE);
          }
      }
  
      public static void main(String[] args) {
          NavegadorDeArquivos navegador = new NavegadorDeArquivos();
          navegador.iniciarNavegacao();
      }
  }
