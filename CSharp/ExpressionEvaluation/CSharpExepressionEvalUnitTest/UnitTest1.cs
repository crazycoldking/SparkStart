using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace CSharpExepressionEvalUnitTest
{
    [TestClass]
    public class UnitTest1
    {
        [TestMethod]
        public void TestCalculation()
        {
            CSharpExpressionEvaluator.CSharpExpEval eval = new CSharpExpressionEvaluator.CSharpExpEval();
            var expression = "1+1*1/1";
            string actual = eval.Execute(expression).ToString();
            string expect = "2";
            Assert.AreEqual(expect, actual);
        }

        [TestMethod]
        public void TestGetDateTime()
        {
            CSharpExpressionEvaluator.CSharpExpEval eval = new CSharpExpressionEvaluator.CSharpExpEval();
            var expression = "DateTime.Now.ToString(\"yyyy-mm-dd\")";
            string actual = eval.Execute(expression).ToString();
            string expect = DateTime.Now.ToString("yyyy-mm-dd");
            Assert.AreEqual(expect, actual);
        }

        [TestMethod]
        public void TestRegularExpression()
        {
            //new Regex("").IsMatch("");
            CSharpExpressionEvaluator.CSharpExpEval eval = new CSharpExpressionEvaluator.CSharpExpEval();
            var expression = "new Regex(\"^(W.?)\").IsMatch(\"Welcome\")";
            string actual = eval.Execute(expression).ToString();
            string expect = "True";
            Assert.AreEqual(expect, actual);
        }
    }
}
