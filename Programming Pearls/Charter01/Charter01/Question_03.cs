using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Charter1
{
    class Question_03
    {
        private const int Range = 10000000;
        public void Quiz()
        {
            List<int> numbers, resultNumbers;
            numbers = CreateRandomList(Range, Range);
            //numbers = CreateRandomList(500, 3000);
            resultNumbers = BitMapSort(Range, numbers);
            numbers.Sort();
            //resultNumbers = BitMapSort(3000, numbers);
            foreach (int number in resultNumbers)
            {
                Console.WriteLine(number);
            }
        }

        private List<int> BitMapSort(int max, List<int> numbers)
        {
            List<int> sortNumber = new List<int>();
            BitArray bits = new BitArray(max);
            bits.SetAll(false);
            numbers.ForEach(number => bits.Set(number, true));
            for (int i = 0; i < max; i++)
            {
                if (bits[i])
                {
                    sortNumber.Add(i);
                }
            }
            return sortNumber;
        }

        private List<int> CreateRandomList(int count, int max)
        {
            List<int> numbers = Enumerable.Range(0, max).ToList();
            numbers = numbers.OrderBy(number => Guid.NewGuid()).ToList();
            return numbers.GetRange(0, count);
        }
    }
}
