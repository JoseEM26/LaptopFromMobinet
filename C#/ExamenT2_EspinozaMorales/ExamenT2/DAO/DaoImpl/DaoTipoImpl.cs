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
    public class DaoTipoImpl : Dao<Tipo>
    {
        public void Create(Tipo t)
        {

            SqlConnection con = null;
            SqlCommand cmd = null;
            int indicador = -1;

            try
            {
                con = new SqlConnection(ConfigurationManager.ConnectionStrings["db"].ConnectionString);
                con.Open();

                cmd = new SqlCommand("usp_CRUD_TIPO", con);
                cmd.CommandType = System.Data.CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@Indicador", "CREATE");
                cmd.Parameters.AddWithValue("@TipoID", 0);
                cmd.Parameters.AddWithValue("@CategoriaID", t.CategoriaID);
                cmd.Parameters.AddWithValue("@Nombre", t.Nombre);
                cmd.Parameters.AddWithValue("@Descripcion", t.Descripcion);


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

                cmd = new SqlCommand("usp_CRUD_TIPO", con);
                cmd.CommandType = System.Data.CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@Indicador", "DELETE");
                cmd.Parameters.AddWithValue("@TipoID", id);
                cmd.Parameters.AddWithValue("@CategoriaID", 0);
                cmd.Parameters.AddWithValue("@Nombre", "");
                cmd.Parameters.AddWithValue("@Descripcion", "");


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

        public List<Tipo> SeleccionarTodo()
        {
            SqlConnection con = null;
            SqlCommand cmd = null;
            SqlDataReader reader = null;
            List<Tipo> lista = new List<Tipo>();
            try
            {
                con = new SqlConnection(ConfigurationManager.ConnectionStrings["db"].ConnectionString);
                con.Open();

                cmd = new SqlCommand("usp_CRUD_TIPO", con);
                cmd.CommandType = System.Data.CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@Indicador", "SELECCIONATODO");
                cmd.Parameters.AddWithValue("@TipoID", 0);
                cmd.Parameters.AddWithValue("@CategoriaID", 0);
                cmd.Parameters.AddWithValue("@Nombre", "");
                cmd.Parameters.AddWithValue("@Descripcion", "");


                reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    Tipo Tipos = new Tipo()
                    {
                        TipoID = reader[0].ToString(),
                        CategoriaID = reader[1].ToString(),
                        Nombre = reader[2].ToString(),
                        Descripcion = reader[3].ToString(),
                        
                    };

                    lista.Add(Tipos);
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

        public Tipo SeleccionarXID(int id)
        {
            SqlConnection con = null;
            SqlCommand cmd = null;
            SqlDataReader reader = null;
            Tipo t = null;


            try
            {
                con = new SqlConnection(ConfigurationManager.ConnectionStrings["db"].ConnectionString);
                con.Open();

                cmd = new SqlCommand("usp_CRUD_TIPO", con);
                cmd.CommandType = System.Data.CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@Indicador", "SELECCIONAXID");
                cmd.Parameters.AddWithValue("@TipoID", id);
                cmd.Parameters.AddWithValue("@CategoriaID", 0);
                cmd.Parameters.AddWithValue("@Nombre", "");
                cmd.Parameters.AddWithValue("@Descripcion", "");

                reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    t = new Tipo()
                    {
                        TipoID = reader[0].ToString(),
                        CategoriaID = reader[1].ToString(),
                        Nombre = reader[2].ToString(),
                        Descripcion = reader[3].ToString(),

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

        public void Update(Tipo t)
        {
            SqlConnection con = null;
            SqlCommand cmd = null;
            int indicador = -1;

            try
            {
                con = new SqlConnection(ConfigurationManager.ConnectionStrings["db"].ConnectionString);
                con.Open();

                cmd = new SqlCommand("usp_CRUD_TIPO", con);
                cmd.CommandType = System.Data.CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@Indicador", "UPDATE");
                cmd.Parameters.AddWithValue("@TipoID", t.TipoID);
                cmd.Parameters.AddWithValue("@CategoriaID", t.CategoriaID);
                cmd.Parameters.AddWithValue("@Nombre", t.Nombre);
                cmd.Parameters.AddWithValue("@Descripcion", t.Descripcion);


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
