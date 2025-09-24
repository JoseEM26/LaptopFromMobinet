using ExamenT2.Models;
using Microsoft.Ajax.Utilities;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Diagnostics;
using System.Linq;
using System.Web;

namespace ExamenT2.DAO.DaoImpl
{
    public class DaoTicketImpl:Dao<Ticket>
    {
        

        public void Create(Ticket t)
        {
            SqlConnection con = null;
            SqlCommand cmd = null;
            int indicador = -1;

            try
            {
                con = new SqlConnection(ConfigurationManager.ConnectionStrings["db"].ConnectionString);
                con.Open();

                cmd = new SqlCommand("usp_CRUD_TICKETS", con);
                cmd.CommandType = System.Data.CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@Indicador", "CREATE");
                cmd.Parameters.AddWithValue("@TicketID", 0);
                cmd.Parameters.AddWithValue("@FechaCreacion", t.FechaCreacion);
                cmd.Parameters.AddWithValue("@FechaCierre", t.FechaCierre);
                cmd.Parameters.AddWithValue("@ItemID", t.ItemID);
                cmd.Parameters.AddWithValue("@AgenciaID", t.AgenciaID);
                cmd.Parameters.AddWithValue("@Resumen", t.Resumen);


                indicador = cmd.ExecuteNonQuery();

            }
            catch (Exception e)
            {
                Debug.WriteLine(e.Message);
            }
            finally
            {
                con.Close();
            }
        }

        public void Delete(int id)
        {
            SqlConnection con = null;
            SqlCommand cmd = null;
            int indicador = -1;

            try
            {
                con = new SqlConnection(ConfigurationManager.ConnectionStrings["db"].ConnectionString);
                con.Open();

                cmd = new SqlCommand("usp_CRUD_TICKETS", con);
                cmd.CommandType = System.Data.CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@Indicador", "DELETE");
                cmd.Parameters.AddWithValue("@TicketID", id);
                cmd.Parameters.AddWithValue("@FechaCreacion", "");
                cmd.Parameters.AddWithValue("@FechaCierre","");
                cmd.Parameters.AddWithValue("@ItemID", 0);
                cmd.Parameters.AddWithValue("@AgenciaID", 0);
                cmd.Parameters.AddWithValue("@Resumen", "");


                indicador = cmd.ExecuteNonQuery();

            }
            catch (Exception e)
            {
                Debug.WriteLine(e.Message);
            }
            finally
            {
                con.Close();
            }
        }

        public List<Ticket> SeleccionarTodo()
        {
            SqlConnection con = null;
            SqlCommand cmd = null;
            SqlDataReader reader = null;
            List<Ticket> lista = new List<Ticket>();
            try
            {
                con = new SqlConnection(ConfigurationManager.ConnectionStrings["db"].ConnectionString);
                con.Open();

                cmd = new SqlCommand("usp_CRUD_TICKETS", con);
                cmd.CommandType = System.Data.CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@Indicador", "SELECCIONATODO");
                cmd.Parameters.AddWithValue("@TicketID", 0);
                cmd.Parameters.AddWithValue("@FechaCreacion", "");
                cmd.Parameters.AddWithValue("@FechaCierre", "");
                cmd.Parameters.AddWithValue("@ItemID", 0);
                cmd.Parameters.AddWithValue("@AgenciaID", 0);
                cmd.Parameters.AddWithValue("@Resumen", "");

                reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    Ticket ticket = new Ticket()
                    {
                        TicketID = reader[0].ToString(),
                        FechaCreacion = Convert.ToDateTime(reader[1].ToString()),
                        FechaCierre = Convert.ToDateTime(reader[2].ToString()),
                        ItemID = Convert.ToInt32(reader[3]),
                        AgenciaID = Convert.ToInt32(reader[4]),
                        Resumen = reader[5].ToString(),
                    };

                    lista.Add(ticket);
                }




            }catch(Exception e)
            {
                Debug.WriteLine(e.Message);
            }
            finally
            {
                con.Close();
                reader.Close();
            }
            return lista;
        }

        public Ticket SeleccionarXID(int id)
        {
            SqlConnection con = null;
            SqlCommand cmd = null;
            SqlDataReader reader = null;
            Ticket t = null;


            try
            {
                con = new SqlConnection(ConfigurationManager.ConnectionStrings["db"].ConnectionString);
                con.Open();

                cmd = new SqlCommand("usp_CRUD_TICKETS", con);
                cmd.CommandType = System.Data.CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@Indicador", "SELECCIONAXID");
                cmd.Parameters.AddWithValue("@TicketID", id);
                cmd.Parameters.AddWithValue("@FechaCreacion", "");
                cmd.Parameters.AddWithValue("@FechaCierre", "");
                cmd.Parameters.AddWithValue("@ItemID", 0);
                cmd.Parameters.AddWithValue("@AgenciaID", 0);
                cmd.Parameters.AddWithValue("@Resumen", "");

                reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                     t = new Ticket()
                    {
                        TicketID = reader[0].ToString(),
                         FechaCreacion = Convert.ToDateTime(reader[1].ToString()),
                         FechaCierre = Convert.ToDateTime(reader[2].ToString()),
                         ItemID = Convert.ToInt32(reader[3]),
                        AgenciaID = Convert.ToInt32(reader[4]),
                        Resumen = reader[5].ToString(),
                    };

                    
                }




            }
            catch (Exception e)
            {
                Debug.WriteLine(e.Message);
            }
            finally
            {
                con.Close();
                reader.Close();
            }
            return t;
        }

        public void Update(Ticket t)
        {
            SqlConnection con = null;
            SqlCommand cmd = null;
            int indicador = -1;

            try
            {
                con = new SqlConnection(ConfigurationManager.ConnectionStrings["db"].ConnectionString);
                con.Open();

                cmd = new SqlCommand("usp_CRUD_TICKETS", con);
                cmd.CommandType = System.Data.CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@Indicador", "UPDATE");
                cmd.Parameters.AddWithValue("@TicketID", t.TicketID);
                cmd.Parameters.AddWithValue("@FechaCreacion", t.FechaCreacion);
                cmd.Parameters.AddWithValue("@FechaCierre", t.FechaCierre);
                cmd.Parameters.AddWithValue("@ItemID", t.ItemID);
                cmd.Parameters.AddWithValue("@AgenciaID", t.AgenciaID);
                cmd.Parameters.AddWithValue("@Resumen", t.Resumen);


                indicador = cmd.ExecuteNonQuery();

            }
            catch (Exception e)
            {
                Debug.WriteLine(e.Message);
            }
            finally
            {
                con.Close();
            }
        }
    }
}