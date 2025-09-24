using ExamenT2.Models;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Diagnostics;
using System.Linq;
using System.Web;

namespace ExamenT2.DAO.List
{
    public class AgenciaList
    {
        public List<Agencia> SeleccionarTodo()
        {
            SqlConnection con = null;
            SqlCommand cmd = null;
            SqlDataReader reader = null;
            List<Agencia> lista = new List<Agencia>();
            try
            {
                con = new SqlConnection(ConfigurationManager.ConnectionStrings["db"].ConnectionString);
                con.Open();

                cmd = new SqlCommand("USP_LISTAR_AGENCIA", con);
                cmd.CommandType = System.Data.CommandType.StoredProcedure;
                

                reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    Agencia Agencia = new Agencia()
                    {
                        AgenciaID = Convert.ToInt32(reader[0]),
                        Nombre = reader[1].ToString()
                    };

                    lista.Add(Agencia);
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

    }
}