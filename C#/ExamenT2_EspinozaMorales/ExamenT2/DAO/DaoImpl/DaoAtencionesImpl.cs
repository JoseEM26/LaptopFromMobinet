using ExamenT2.Models;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Diagnostics;
using System.Linq;
using System.Web;

namespace ExamenT2.DAO.DaoImpl
{
    public class DaoAtencionesImpl:Dao<Atenciones>
    {

        public void Create(Atenciones t)
        {
            SqlConnection con = null;
            SqlCommand cmd = null;
            int indicador = -1;

            try
            {
                con = new SqlConnection(ConfigurationManager.ConnectionStrings["db"].ConnectionString);
                con.Open();

                cmd = new SqlCommand("usp_CRUD_AtencionES", con);
                cmd.CommandType = System.Data.CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@Indicador", "CREATE");
                cmd.Parameters.AddWithValue("@IDTICKET", t.TicketID);
                cmd.Parameters.AddWithValue("@FECHAPROGRAMADA", t.FechaProgramada);
                cmd.Parameters.AddWithValue("@PROVEDORID", t.ProveedorID);
                cmd.Parameters.AddWithValue("@IDITEM", t.ItemID);
                cmd.Parameters.AddWithValue("@TIPOtICKET", t.TipoTicket);
                cmd.Parameters.AddWithValue("@ESTADOID", t.EstadoID);
                cmd.Parameters.AddWithValue("@COSTOATENCION", t.CostoAtencion);


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

                cmd = new SqlCommand("usp_CRUD_AtencionES", con);
                cmd.CommandType = System.Data.CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@Indicador", "DELETE");
                cmd.Parameters.AddWithValue("@IDTICKET", id);
                cmd.Parameters.AddWithValue("@FECHAPROGRAMADA", "");
                cmd.Parameters.AddWithValue("@PROVEDORID", 0);
                cmd.Parameters.AddWithValue("@IDITEM",0);
                cmd.Parameters.AddWithValue("@TIPOtICKET", "");
                cmd.Parameters.AddWithValue("@ESTADOID", 0);
                cmd.Parameters.AddWithValue("@COSTOATENCION", 0);


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

        public List<Atenciones> SeleccionarTodo()
        {
            SqlConnection con = null;
            SqlCommand cmd = null;
            SqlDataReader reader = null;
            List<Atenciones> lista = new List<Atenciones>();
            try
            {
                con = new SqlConnection(ConfigurationManager.ConnectionStrings["db"].ConnectionString);
                con.Open();

                cmd = new SqlCommand("usp_CRUD_AtencionES", con);
                cmd.CommandType = System.Data.CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@Indicador", "SELECCIONATODO");
                cmd.Parameters.AddWithValue("@IDTICKET", 0);
                cmd.Parameters.AddWithValue("@FECHAPROGRAMADA", "");
                cmd.Parameters.AddWithValue("@PROVEDORID", 0);
                cmd.Parameters.AddWithValue("@IDITEM", 0);
                cmd.Parameters.AddWithValue("@TIPOtICKET", "");
                cmd.Parameters.AddWithValue("@ESTADOID", 0);
                cmd.Parameters.AddWithValue("@COSTOATENCION", 0);

                reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    Atenciones a = new Atenciones()
                    {
                        TicketID = Convert.ToInt32(reader[0]),
                        FechaProgramada = Convert.ToDateTime(reader[1].ToString()),
                        ProveedorID = Convert.ToInt32(reader[2].ToString()),
                        ItemID = Convert.ToInt32(reader[3]),
                        TipoTicket = reader[4].ToString(),
                        EstadoID= Convert.ToInt32(reader[5]),
                        CostoAtencion= Convert.ToDecimal(reader[6])
                    };

                    lista.Add(a);
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
            return lista;
        }

        public Atenciones SeleccionarXID(int id)
        {
            SqlConnection con = null;
            SqlCommand cmd = null;
            SqlDataReader reader = null;
            Atenciones t = null;


            try
            {
                con = new SqlConnection(ConfigurationManager.ConnectionStrings["db"].ConnectionString);
                con.Open();

                cmd = new SqlCommand("usp_CRUD_AtencionES", con);
                cmd.CommandType = System.Data.CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@Indicador", "SELECCIONAXID");
                cmd.Parameters.AddWithValue("@IDTICKET", id);
                cmd.Parameters.AddWithValue("@FECHAPROGRAMADA", "");
                cmd.Parameters.AddWithValue("@PROVEDORID", 0);
                cmd.Parameters.AddWithValue("@IDITEM", 0);
                cmd.Parameters.AddWithValue("@TIPOtICKET", "");
                cmd.Parameters.AddWithValue("@ESTADOID", 0);
                cmd.Parameters.AddWithValue("@COSTOATENCION", 0);

                reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    t = new Atenciones()
                    {
                        TicketID = Convert.ToInt32(reader[0]),
                        FechaProgramada = Convert.ToDateTime(reader[1].ToString()),
                        ProveedorID = Convert.ToInt32(reader[2].ToString()),
                        ItemID = Convert.ToInt32(reader[3]),
                        TipoTicket = reader[4].ToString(),
                        EstadoID = Convert.ToInt32(reader[5]),
                        CostoAtencion = Convert.ToDecimal(reader[6])
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

        public void Update(Atenciones t)
        {
            SqlConnection con = null;
            SqlCommand cmd = null;
            int indicador = -1;

            try
            {
                con = new SqlConnection(ConfigurationManager.ConnectionStrings["db"].ConnectionString);
                con.Open();

                cmd = new SqlCommand("usp_CRUD_AtencionES", con);
                cmd.CommandType = System.Data.CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@Indicador", "UPDATE");
                cmd.Parameters.AddWithValue("@IDTICKET", t.TicketID);
                cmd.Parameters.AddWithValue("@FECHAPROGRAMADA", t.FechaProgramada);
                cmd.Parameters.AddWithValue("@PROVEDORID", t.ProveedorID);
                cmd.Parameters.AddWithValue("@IDITEM", t.ItemID);
                cmd.Parameters.AddWithValue("@TIPOtICKET", t.TipoTicket);
                cmd.Parameters.AddWithValue("@ESTADOID", t.EstadoID);
                cmd.Parameters.AddWithValue("@COSTOATENCION", t.CostoAtencion);

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