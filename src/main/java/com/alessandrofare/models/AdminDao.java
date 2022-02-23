package com.alessandrofare.models;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import com.alessandrofare.connection.ConnectionFactory;

/*
 * Classe importante per la gestione dei dati nel database.
 * Gestisce il database votoelettronico in MySql e si connette ad esso tramite la classe ConnectionFactory nel package connection.
 */
public class AdminDao {
	
	
	@SuppressWarnings("resource")
	public void registra(Elettore el) throws SQLException {
		Connection c = ConnectionFactory.getConnection();
		 
        PreparedStatement stmt = null;

        try {
        	stmt = c.prepareStatement("SELECT * FROM elettore WHERE codFiscale=?");
        	stmt.setString(1,el.getCF());
            ResultSet rs = stmt.executeQuery();
            boolean trovato = rs.next();
            if(trovato) {
            	stmt = c.prepareStatement("UPDATE elettore SET countVoto=? WHERE codFiscale=?");               
                stmt.setInt(1, rs.getInt("countVoto")+1);
                stmt.setString(2,  el.getCF());
                el.setCountVoto(1);
                stmt.executeUpdate();
            	JOptionPane.showMessageDialog(null, "Sei già registrato!");
            	return;
            }
            stmt = c.prepareStatement("INSERT INTO elettore(nome,cognome,sesso,nascita,citta,stato,codFiscale,countVoto)VALUES(?,?,?,?,?,?,?,?)");
            stmt.setString(1,el.getNome());
            stmt.setString(2,el.getCognome());
            stmt.setString(3,el.getSesso());
            stmt.setString(4,el.getNascita());
            stmt.setString(5,el.getCitta());
            stmt.setString(6,el.getStato());
            stmt.setString(7,el.getCF());
            stmt.setInt(8,el.getCountVoto());
            
            if(el.checkMaggiorenne() == false) {
            	JOptionPane.showMessageDialog(null, "Non sei maggiorenne! Non puoi votare!");
            	return;
            }
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvato con successo!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(c, stmt);
        }
        
	}

	
	public List<Elettore> allElettori() throws SQLException, IOException {
		
		
		Connection c = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Elettore> elettori = new ArrayList<Elettore>();

        try {
            stmt = c.prepareStatement("SELECT * FROM elettore");
            rs = stmt.executeQuery();

            while (rs.next()) {

                
                Elettore e = new Elettore();

                e.setNome(rs.getString("nome"));
                e.setCognome(rs.getString("cognome"));
                e.setSesso(rs.getString("sesso"));
                e.setNascitaString(rs.getString("nascita"));
                e.setCitta(rs.getString("citta"));
                e.setStato(rs.getString("stato"));
                e.setCF(rs.getString("codFiscale"));
                e.setCountVoto(rs.getInt("countVoto"));
                elettori.add(e);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore");
        } finally {
            ConnectionFactory.closeConnection(c, stmt, rs);
        }

        return elettori;

    }
	
	
	public int deleteAllElettori() throws SQLException {
		Connection c = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

        try {
            stmt = c.prepareStatement("DELETE FROM elettore");

            int rowsAffected = stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Eliminati con successo!");
            return rowsAffected;
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Errore di eliminazione: " + ex);
        } finally {
            ConnectionFactory.closeConnection(c, stmt);
        }
		return 0;
	}
	
	
	public int delete(Elettore el) throws SQLException {
		Connection c = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

        try {
            stmt = c.prepareStatement("DELETE FROM elettore WHERE codFiscale=?");
            stmt.setString(1, el.getCF());

            int rowsAffected = stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Eliminato con successo!");
            return rowsAffected;
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Errore di eliminazione: " + ex);
        } finally {
            ConnectionFactory.closeConnection(c, stmt);
        }
		return 0;
        
    }
	
	
	public void update(Elettore el) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE elettore SET nome = ? ,cognome = ?,sesso = ?,nascita = ?,citta = ?,stato = ? WHERE codFiscale = ?");
            
            stmt.setString(1, el.getNome());
            stmt.setString(2, el.getCognome());
            stmt.setString(3, el.getSesso());
            stmt.setString(4, el.getNascita());
            stmt.setString(5, el.getCitta());
            stmt.setString(6, el.getStato());
            stmt.setString(7, el.getCF());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Aggiornato con successo!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Errore di aggiornamento: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    
	@SuppressWarnings("resource")
	public void registra(Utente u) throws SQLException {
		Connection c = ConnectionFactory.getConnection();
		 
        PreparedStatement stmt = null;
        
        try {
        	stmt = c.prepareStatement("SELECT * FROM utente WHERE username=? AND password=?");
        	stmt.setString(1,u.getUsername());
            stmt.setString(2,u.getPassword());
            ResultSet rs = stmt.executeQuery();
            boolean trovato = rs.next();
            if(trovato) {
            	if(u.getIsAdmin() == true) {
            		JOptionPane.showMessageDialog(null, "Bentornato!");
                	return;
            	}
            	else {
	            	JOptionPane.showMessageDialog(null, "Sei già registrato!");
	            	return;
            	}
            }
            stmt = c.prepareStatement("INSERT INTO utente(username, password, isAdmin)VALUES(?,?,?)");
            stmt.setString(1,u.getUsername());
            stmt.setString(2,u.getPassword());
            stmt.setBoolean(3,u.getIsAdmin());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvato con successo!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(c, stmt);
        }
        
	}

	
	public List<Utente> allUtenti() throws SQLException, IOException {
		
		
		Connection c = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Utente> utenti = new ArrayList<Utente>();

        try {
            stmt = c.prepareStatement("SELECT * FROM utente");
            rs = stmt.executeQuery();

            while (rs.next()) {

                
            	Utente u = new Admin();

                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setIsAdmin(rs.getBoolean("isAdmin"));
                
                utenti.add(u);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore");
        } finally {
            ConnectionFactory.closeConnection(c, stmt, rs);
        }

        return utenti;

    }
	

	public int deleteAllUtenti() throws SQLException {
		Connection c = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

        try {
            stmt = c.prepareStatement("DELETE FROM utente");
          
            int rowsAffected = stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Eliminati con successo!");
            return rowsAffected;
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Errore di eliminazione: " + ex);
        } finally {
            ConnectionFactory.closeConnection(c, stmt);
        }
		return 0;
	}
	
	
	public int delete(Utente u) throws SQLException {
		Connection c = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

        try {
            stmt = c.prepareStatement("DELETE FROM utente WHERE username=? AND password=?");
            stmt.setString(1, u.getUsername());
            stmt.setString(2, u.getPassword());
            int rowsAffected = stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Eliminato con successo!");
            return rowsAffected;
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Errore di eliminazione: " + ex);
        } finally {
            ConnectionFactory.closeConnection(c, stmt);
        }
		return 0;
        
    }
	
	
	public void update(Utente u) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE utente SET username = ? ,password = ?,isAdmin = ? WHERE username=? AND password=?");
            
            stmt.setString(1, u.getUsername());
            stmt.setString(2, u.getPassword());
            stmt.setBoolean(3, u.getIsAdmin());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Aggiornato con successo!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Errore di aggiornamento: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    

	@SuppressWarnings("resource")
	public List<Partito> allPartiti() throws SQLException, IOException {
		
		
		Connection c = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Partito> partiti = new ArrayList<Partito>();
        try {
        	
            stmt = null;
            stmt = c.prepareStatement("SELECT * FROM partito");
            
            
            rs = stmt.executeQuery();
            while(rs.next()) {
            	Partito p = new Partito();
            	p.setNome(rs.getString("pnome"));
            	p.setPosizione(rs.getInt("posizione"));
            	p.setPreferenza(rs.getBoolean("preferenza"));
            	p.setIdSchedaP(rs.getInt("idSchedaP"));
            	partiti.add(p);
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore");
        } finally {
            ConnectionFactory.closeConnection(c, stmt, rs);
        }
		return partiti;

       

    }
	
	@SuppressWarnings("resource")
	public List<Partito> allPartitiScheda(String idScheda) throws SQLException, IOException {
		
		
		Connection c = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = Integer.parseInt(idScheda);
        List<Partito> partiti = new ArrayList<Partito>();
        try {
        	
            stmt = null;
            stmt = c.prepareStatement("SELECT * FROM partito WHERE idSchedaP=?");
            stmt.setInt(1, id);
            
            rs = stmt.executeQuery();
            while(rs.next()) {
            	Partito p = new Partito();
            	p.setNome(rs.getString("pnome"));
            	p.setPosizione(rs.getInt("posizione"));
            	p.setPreferenza(rs.getBoolean("preferenza"));
            	p.setIdSchedaP(rs.getInt("idSchedaP"));
            	partiti.add(p);
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore");
        } finally {
            ConnectionFactory.closeConnection(c, stmt, rs);
        }
		return partiti;

       

    }
	
	@SuppressWarnings("resource")
	public List<Candidato> allPartito(String nomePartito) throws SQLException, IOException {
		
		
		Connection c = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Candidato> candidati = new ArrayList<Candidato>();
        
        try {
        	
            stmt = null;
            stmt = c.prepareStatement("SELECT * FROM candidato WHERE nomePartito=?");
            stmt.setString(1, nomePartito);
            
            rs = stmt.executeQuery();
            while(rs.next()) {
            	Candidato candidato = new Candidato();
            	candidato.setNome(rs.getString("nome"));
            	candidato.setCognome(rs.getString("cognome"));
            	candidato.setNomePartito(rs.getString("nomePartito"));
            	candidato.setNumVoti(rs.getInt("numVoti"));
            	candidato.setPosizione(rs.getInt("posizione"));
            	candidato.setPreferenza(rs.getBoolean("preferenza"));
                candidati.add(candidato);
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore");
        } finally {
            ConnectionFactory.closeConnection(c, stmt, rs);
        }
		return candidati;

       

    }
	
	public List<Candidato> allCandidati() throws SQLException, IOException {
		
		
		Connection c = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Candidato> candidati = new ArrayList<Candidato>();

        try {
            stmt = c.prepareStatement("SELECT * FROM candidato");
            rs = stmt.executeQuery();

            while (rs.next()) {

                
            	Candidato candidato = new Candidato();
            	candidato.setNome(rs.getString("nome"));
            	candidato.setCognome(rs.getString("cognome"));
            	candidato.setNomePartito(rs.getString("nomePartito"));
            	candidato.setNumVoti(rs.getInt("numVoti"));
            	candidato.setPosizione(rs.getInt("posizione"));
            	candidato.setPreferenza(rs.getBoolean("preferenza"));
                candidati.add(candidato);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore");
        } finally {
            ConnectionFactory.closeConnection(c, stmt, rs);
        }

        return candidati;

    }
	

	public int deleteAllPartiti() throws SQLException {
		Connection c = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

        try {
            stmt = c.prepareStatement("DELETE FROM partito");
      
            int rowsAffected = stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Eliminati con successo!");
            return rowsAffected;
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Errore di eliminazione: " + ex);
        } finally {
            ConnectionFactory.closeConnection(c, stmt);
        }
		return 0;
	}
	

	public int delete(Partito p) throws SQLException {
		Connection c = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

        try {
            stmt = c.prepareStatement("DELETE FROM partito WHERE pnome=?");
            stmt.setString(1, p.getNome());
            int rowsAffected = stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Eliminato con successo!");
            return rowsAffected;
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Errore di eliminazione: " + ex);
        } finally {
            ConnectionFactory.closeConnection(c, stmt);
        }
		return 0;
        
    }
	
	public int deleteAllCandidati() throws SQLException {
		Connection c = ConnectionFactory.getConnection();
		PreparedStatement pstmt = c.prepareStatement("DELETE FROM candidato");

		int rowsAffected = pstmt.executeUpdate();
		
		pstmt.close();
		c.close();
		
		return rowsAffected;
	}
	
	
	public int delete(Candidato candidato) throws SQLException {
		Connection c = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

        try {
            stmt = c.prepareStatement("DELETE FROM candidato WHERE nome=? AND cognome=?");
            stmt.setString(1, candidato.getNome());
            stmt.setString(2,  candidato.getCognome());
            int rowsAffected = stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Eliminato con successo!");
            return rowsAffected;
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Errore di eliminazione: " + ex);
        } finally {
            ConnectionFactory.closeConnection(c, stmt);
        }
		return 0;
        
    }
	
	@SuppressWarnings("resource")
	public SchedaElettorale selectSchedaReferendum(SchedaElettorale el) throws SQLException {
		Connection c = ConnectionFactory.getConnection();
		 
        PreparedStatement stmt = null;

        try {
        	stmt = c.prepareStatement("SELECT * FROM scheda WHERE domanda=?");
        	stmt.setString(1,el.getReferendum().getDomanda());
            ResultSet rs = stmt.executeQuery();
            boolean trovato = rs.next();
            if(trovato) {
            	return el;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(c, stmt);
        }
		return null;
        
	}
	

	public List<String> listReferendum() {
		Connection c = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
        ResultSet rs = null;

        List<String> ref = new ArrayList<String>();

        try {
            stmt = c.prepareStatement("SELECT * FROM referendum");
            rs = stmt.executeQuery();

            while (rs.next()) {
            	String domanda = rs.getString("domanda"); 
                ref.add(domanda);            	
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore");
        } finally {
            ConnectionFactory.closeConnection(c, stmt, rs);
        }

        return ref;

    }
	
	@SuppressWarnings("resource")
	public void updateReferendum(Referendum r) {

		Connection c = ConnectionFactory.getConnection();
		 
        PreparedStatement stmt = null;

        try {
        	stmt = c.prepareStatement("SELECT * FROM referendum WHERE domanda=?");
        	stmt.setString(1,r.getDomanda());
            ResultSet rs = stmt.executeQuery();
            boolean trovato = rs.next();
            if(trovato) {
            	stmt = c.prepareStatement("UPDATE referendum SET numVotiPositivi = ?, numVotiNegativi = ?, numVoti = ? WHERE domanda= ?");  
            	if(r.getFavorevole() == true) {
            		stmt.setInt(1, rs.getInt("numVotiPositivi")+1);
                    stmt.setInt(2,   rs.getInt("numVotiNegativi"));
                    stmt.setInt(3,  rs.getInt("numVoti")+1);
                    stmt.setString(4, r.getDomanda());
                    JOptionPane.showMessageDialog(null, "Grazie del voto");
                	stmt.executeUpdate();
                	return;
            	}
            	else {
            		stmt.setInt(1, rs.getInt("numVotiPositivi"));
                    stmt.setInt(2,   rs.getInt("numVotiNegativi")+1);
                    stmt.setInt(3,  rs.getInt("numVoti")+1);
                    stmt.setString(4, r.getDomanda());
                    JOptionPane.showMessageDialog(null, "Grazie del voto");
                	stmt.executeUpdate();
                	return;
            	}
                
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(c, stmt);
        }
        
	}
	
	public List<String> listCandidati() {
		Connection c = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
        ResultSet rs = null;

        List<String> ref = new ArrayList<String>();

        try {
            stmt = c.prepareStatement("SELECT * FROM candidato");
            rs = stmt.executeQuery();

            while (rs.next()) {
            	String domanda = rs.getString("nome") + " " + rs.getString("cognome")+ " " + rs.getString("nomePartito"); 
                ref.add(domanda);            	
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore");
        } finally {
            ConnectionFactory.closeConnection(c, stmt, rs);
        }

        return ref;

    }
	
	public List<String> listPartiti() {
		Connection c = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
        ResultSet rs = null;

        List<String> ref = new ArrayList<String>();

        try {
            stmt = c.prepareStatement("SELECT * FROM partito");
            rs = stmt.executeQuery();

            while (rs.next()) {
            	String domanda = rs.getString("pnome");
                ref.add(domanda);            	
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore");
        } finally {
            ConnectionFactory.closeConnection(c, stmt, rs);
        }

        return ref;

    }
    
	@SuppressWarnings("resource")
	public Map<String, Integer> selectReferendum(String domanda) {

		Connection c = ConnectionFactory.getConnection();
		 
        PreparedStatement stmt = null;

        try {
        	stmt = c.prepareStatement("SELECT * FROM referendum WHERE domanda=?");
        	stmt.setString(1, domanda);
            ResultSet rs = stmt.executeQuery();
            boolean trovato = rs.next();
            if(trovato) {
            	Referendum r = new Referendum(rs.getString("domanda"), rs.getDouble("quorum"));
            	r.setNumVoti(rs.getInt("numVoti"));
            	r.setNumVotiPositivi(rs.getInt("numVotiPositivi"));
            	r.setNumVotiNegativi(rs.getInt("numVotiNegativi"));
            	
            	if(rs.getDouble("quorum") == 0) {
            		Vincita vincita = new Vincita(VincitaType.referendumSenzaQuorum);
            		System.out.println("senza quorum");
            		vincita.setReferendum(r);
            		return vincita.vincitaReferendum();
            	}
            	else {
            		Vincita vincita = new Vincita(VincitaType.referendumConQuorum);
            		vincita.setReferendum(r);
            		return vincita.vincitaReferendum();
            	}
            	
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(c, stmt);
        }
		return null;
        
	}
	
	
	@SuppressWarnings("resource")
	public String selectScheda(Partito p) throws SQLException, IOException {
		
		Connection c = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
        ResultSet rs = null;
        
        
        try {
        	
            stmt = null;
            stmt = c.prepareStatement("SELECT * FROM scheda WHERE idScheda=?");
            stmt.setInt(1, p.getIdSchedaP());
            rs = stmt.executeQuery();
            boolean trovato = rs.next();
            
            if(trovato) {
            	String vincita = rs.getString("vincita");
            	
            	return vincita;
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore");
        } finally {
            ConnectionFactory.closeConnection(c, stmt, rs);
        }
		return null;      

    }
	
	public List<String> listIdScheda() {
		Connection c = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
        ResultSet rs = null;

        List<String> ref = new ArrayList<String>();

        try {
            stmt = c.prepareStatement("SELECT * FROM scheda");
            rs = stmt.executeQuery();

            while (rs.next()) {
            	String partito = rs.getString("partito");
            	if(!partito.equals("null")) {
	            	String domanda = String.valueOf(rs.getInt("idScheda"));
	                ref.add(domanda); 
            	}
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore");
        } finally {
            ConnectionFactory.closeConnection(c, stmt, rs);
        }

        return ref;

    }
	
	@SuppressWarnings("resource")
	public List<SchedaElettorale> selectSchedaVotazione(String idScheda) throws SQLException, IOException {
		
		Connection c = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = Integer.parseInt(idScheda);
        List<SchedaElettorale> schede = new ArrayList<SchedaElettorale>();
        try {
        	
            stmt = null;
            stmt = c.prepareStatement("SELECT * FROM scheda WHERE idScheda=?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
            	String vincita = rs.getString("vincita");
            	String voto = rs.getString("voto");
            	String partito = rs.getString("partito");
            	VotoType vt = VotoType.valueOf(voto);
            	VincitaType vit = VincitaType.valueOf(vincita);
            	Voto votoScheda = new Voto(vt);
            	Vincita vincitaScheda = new Vincita(vit);
            	
            	stmt = null;
            	stmt = c.prepareStatement("SELECT * FROM partito WHERE idSchedaP=?");
            	stmt.setInt(1, id);
            	rs = stmt.executeQuery();
            	while(rs.next()) {
            		Partito p = new Partito();
                	p.setNome(rs.getString("pnome"));
                	p.setPosizione(rs.getInt("posizione"));
                	p.setPreferenza(rs.getBoolean("preferenza"));
                	p.setIdSchedaP(rs.getInt("idSchedaP"));
                	List<Candidato> candidati = this.allPartito(p.getNome());
                	p.setCandidati(candidati);
                	SchedaElettorale s = new SchedaElettorale(id,votoScheda, vincitaScheda, p);
                	schede.add(s);
            	}
           
            }
            return schede;

        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore");
        } finally {
            ConnectionFactory.closeConnection(c, stmt, rs);
        }
		return null;      

    }
	
	
	@SuppressWarnings("resource")
	public void addPartito(String nome) throws SQLException {
		Connection c = ConnectionFactory.getConnection();
		 
        PreparedStatement stmt = null;
        
        try {
        	stmt = c.prepareStatement("SELECT * FROM partito WHERE pnome=?");
        	stmt.setString(1,nome);
            ResultSet rs = stmt.executeQuery();
            boolean trovato = rs.next();
            if(trovato) {
            	JOptionPane.showMessageDialog(null, "E' già registrato!");
            	return;
            }
            stmt = c.prepareStatement("INSERT INTO partito(pnome,posizione,preferenza)VALUES(?,?,?)");
            stmt.setString(1,nome);
            stmt.setInt(2, 0);
            stmt.setBoolean(3, false);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvato con successo!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(c, stmt);
        }
        
	}
	
	@SuppressWarnings("resource")
	public void addCandidato(String nome, String cognome, String nomePartito) throws SQLException {
		Connection c = ConnectionFactory.getConnection();
		 
        PreparedStatement stmt = null;
        
        try {
        	stmt = c.prepareStatement("SELECT * FROM candidato WHERE nome=? AND cognome=?");
        	stmt.setString(1,nome);
        	stmt.setString(2,cognome);
            ResultSet rs = stmt.executeQuery();
            boolean trovato = rs.next();
            if(trovato) {
            	JOptionPane.showMessageDialog(null, "E' già registrato!");
            	return;
            }
            stmt = c.prepareStatement("INSERT INTO candidato(nome, cognome, nomePartito, numVoti, posizione, preferenza)VALUES(?,?,?,?,?,?)");
            stmt.setString(1,nome);
            stmt.setString(2,cognome);
            stmt.setString(3,nomePartito);
            stmt.setInt(4, 0);
            stmt.setInt(5, 0);
            stmt.setBoolean(6, false);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvato con successo!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(c, stmt);
        }
        
	}
	
	
	@SuppressWarnings("resource")
	public void addScheda(String voto, String vincita) throws SQLException {
		Connection c = ConnectionFactory.getConnection();
		
        PreparedStatement stmt = null;
        int max = 500000;
    	int min = 1;
    	int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        try {
        	stmt = c.prepareStatement("SELECT * FROM partito");
        	
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	Partito p = new Partito();
            	p.setNome(rs.getString("pnome"));
            	
            	
                stmt = c.prepareStatement("INSERT INTO scheda(idScheda, voto, vincita, partito, referendum)VALUES(?,?,?,?,?)");
                stmt.setInt(1,random_int);
                stmt.setString(2,voto);
                stmt.setString(3,vincita);
                stmt.setString(4,p.getNome());
                stmt.setString(5,"null");
                stmt.executeUpdate();
                
                stmt = c.prepareStatement("UPDATE partito SET idSchedaP=? WHERE pnome=?");  
            	
        		stmt.setInt(1, random_int);
                stmt.setString(2,  p.getNome());
                
            	stmt.executeUpdate();
                	
            	
            }
            
            JOptionPane.showMessageDialog(null, "Inserito con successo!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(c, stmt);
        }
        
	}
	
	
	@SuppressWarnings("resource")
	public void addScheda(String vincita, String domanda, String quorum) throws SQLException {
		Connection c = ConnectionFactory.getConnection();
		
        PreparedStatement stmt = null;
        int max = 500000;
    	int min = 1;
    	int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
    	double q;
        try {
        	            	            	
            stmt = c.prepareStatement("INSERT INTO scheda(idScheda, voto, vincita, partito, referendum)VALUES(?,?,?,?,?)");
            stmt.setInt(1,random_int);
            stmt.setString(2, "referendum");
            stmt.setString(3,vincita);
            stmt.setString(4,"null");
            stmt.setString(5,domanda);
            stmt.executeUpdate();
            
            stmt = c.prepareStatement("INSERT INTO referendum(domanda, quorum, numVotiPositivi, numVotiNegativi, numVoti)VALUES(?,?,?,?,?)");  
        	
    		stmt.setString(1, domanda);
    		if(quorum.equals(""))
    			q = 0;
    		else
    			q = Double.parseDouble(quorum);
            stmt.setDouble(2, q);
            stmt.setInt(3, 0);
            stmt.setInt(4, 0);
            stmt.setInt(5, 0);
        	stmt.executeUpdate();
                	           	                       
            JOptionPane.showMessageDialog(null, "Inserito con successo!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(c, stmt);
        }
        
	}
	

	@SuppressWarnings("resource")
	public String selectSchedaVoto(String idScheda) throws SQLException, IOException {
		
		Connection c = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
        ResultSet rs = null;
        
        
        try {
        	int id = Integer.parseInt(idScheda);
            stmt = null;
            stmt = c.prepareStatement("SELECT * FROM scheda WHERE idScheda=?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            boolean trovato = rs.next();
            
            if(trovato) {
            	String voto = rs.getString("voto");
            	
            	return voto;
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore");
        } finally {
            ConnectionFactory.closeConnection(c, stmt, rs);
        }
		return null;      

    }
	
	
	@SuppressWarnings("resource")
	public void updateCandidato(String nome, String cognome) throws SQLException {
		Connection c = ConnectionFactory.getConnection();
		 
        PreparedStatement stmt = null;

        try {
        	stmt = c.prepareStatement("SELECT * FROM candidato WHERE nome=? AND cognome=?");
        	stmt.setString(1,nome);
        	stmt.setString(2,cognome);
            ResultSet rs = stmt.executeQuery();
            boolean trovato = rs.next();
            if(trovato) {
            	stmt = c.prepareStatement("UPDATE candidato SET numVoti=? WHERE nome=? AND cognome=?");               
                stmt.setInt(1, rs.getInt("numVoti")+1);
                stmt.setString(2, nome);
                stmt.setString(3, cognome);
                stmt.executeUpdate();
            	JOptionPane.showMessageDialog(null, "Grazie del voto!");
            	return;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(c, stmt);
        }
        
	}
	
	
	@SuppressWarnings("resource")
	public List<String> selectPartitoVoto(String idScheda) throws SQLException, IOException {
		List<String> partiti = new ArrayList<String>();
		Connection c = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
        ResultSet rs = null;
        
        
        try {
        	int id = Integer.parseInt(idScheda);
            stmt = null;
            stmt = c.prepareStatement("SELECT * FROM partito WHERE idSchedaP=?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while(rs.next()) {
            	String nomePartito = rs.getString("nomePartito");
            	
            	partiti.add(nomePartito);
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore");
        } finally {
            ConnectionFactory.closeConnection(c, stmt, rs);
        }
		return partiti;      

    }
	

	@SuppressWarnings("resource")
	public List<String> selectCandidatiPartito(String nomePartito) throws SQLException, IOException {
				
		Connection c = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> candidati = new ArrayList<String>();
        
        try {
        	
            stmt = null;
            stmt = c.prepareStatement("SELECT * FROM candidato WHERE nomePartito=?");
            stmt.setString(1, nomePartito);
            
            rs = stmt.executeQuery();
            while(rs.next()) {
            	String nome = rs.getString("nome") + " " + rs.getString("cognome");
                candidati.add(nome);
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore");
        } finally {
            ConnectionFactory.closeConnection(c, stmt, rs);
        }
		return candidati;

       

    }
	
	
	@SuppressWarnings("resource")
	public void updateCandidatoPartito(String nome) throws SQLException {
		Connection c = ConnectionFactory.getConnection();
		 
        PreparedStatement stmt = null;

        try {
        	stmt = c.prepareStatement("SELECT * FROM candidato WHERE nomePartito=?");
        	stmt.setString(1,nome);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	stmt = c.prepareStatement("UPDATE candidato SET numVoti=? WHERE nomePartito=?");               
                stmt.setInt(1, rs.getInt("numVoti")+1);
                stmt.setString(2,nome);
                stmt.executeUpdate();
            	
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(c, stmt);
        }
        
	}
	
	
	@SuppressWarnings("resource")
	public void updateCandidatoOrdinale(String nome, String cognome, String posizione) {
		Connection c = ConnectionFactory.getConnection();
		int n = Integer.parseInt(posizione);
        PreparedStatement stmt = null;
        int d = 0;
        if(n==1)
        	d = 5;
        else if(n==2)
        	d = 4;
        else if(n==3)
        	d = 3;
        else if(n==4)
        	d = 2;
        else if(n==5)
        	d = 1;
        else
        	d = 0;
        try {
        	stmt = c.prepareStatement("SELECT * FROM candidato WHERE nome=? AND cognome=?");
        	stmt.setString(1,nome);
        	stmt.setString(2,cognome);
            ResultSet rs = stmt.executeQuery();
            boolean trovato = rs.next();
            if(trovato) {
            	stmt = c.prepareStatement("UPDATE candidato SET numVoti=?, posizione=? WHERE nome=? AND cognome=?");               
                stmt.setInt(1, rs.getInt("numVoti")+d);
                stmt.setInt(2, n);
                stmt.setString(3,nome);
            	stmt.setString(4,cognome);
                stmt.executeUpdate();
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(c, stmt);
        }
	}
}
